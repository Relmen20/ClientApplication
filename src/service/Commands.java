package service;

import entity.CommandList;
import controller.SocketSender;

import entity.EntityUser;
import entity.SerializedEntity;

import java.util.HashMap;
import java.util.Scanner;

import static entity.CommandList.getStringEnum;

public class Commands {
    private Scanner scanner;
    private UserInteraction userInteraction;

    public Commands(Scanner scanner){
         this.scanner = scanner;
         this.userInteraction = new UserInteraction(scanner);
    }

    public void comandHandler() {

        HashMap<String, Object> sendData = new HashMap<>();

        HashMap<String, Object> receiveData;

        SocketSender socketSender;

        System.out.println("Enter the message");
        String scan = scanner.nextLine();
        CommandList commandList = getStringEnum().get(scan);
        if (commandList != null) {
            socketSender = new SocketSender();
            switch (commandList) {
                case HELP:
                    commandList.printHelp();
                    break;
                case READ:
                    System.out.println("Please enter an ID or write 'all' to see all users");

                    sendData.put(scan, tryToParse(scanner.nextLine()));
                    socketSender.sender(sendData);

                    receiveData = socketSender.catcher();
                    System.out.println(receiveData);
                    break;


                case CREATE:
                    System.out.println("Create new user");
                    EntityUser user = userInteraction.createUser();

                    sendData.put(scan, user);
                    socketSender.sender(sendData);

                    receiveData = socketSender.catcher();
                    System.out.println(receiveData);
                    break;

                case DELETE:
//                    userInteraction.removeUsers();
                    break;
            }
            socketSender.stopAllProcess();
        } else {
            System.out.println("Wrong command, pls use help");
        }
    }

    public int tryToParse(String param){
        int in = 0;
        try{
            in = Integer.parseInt(param);
        }catch (Exception e){
            return in;
        }
        return in;
    }
}
