package service;

import controller.SocketSender;
import model.CommandList;
import model.EntityUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static model.CommandList.getStringEnum;

public class CommandService {
    private Scanner scanner;
    private UserInteraction userInteraction;

    public CommandService(Scanner scanner) {
        this.scanner = scanner;
        this.userInteraction = new UserInteraction(scanner);
    }

    public void process() {

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
                    System.out.println("Please enter an ID of user");

                    String readParam;
                    do {
                        readParam = scanner.nextLine();
                    } while (!checkInt(readParam));

                    sendData.put(scan, tryToParse(readParam));
                    socketSender.sender(sendData);

                    receiveData = socketSender.catcher();
                    System.out.println(receiveData.get(scan).toString());
                    break;

                case READ_ALL:
                    System.out.println("============== All users ==============");

                    sendData.put(scan, "all");
                    socketSender.sender(sendData);

                    receiveData = socketSender.catcher();
                    printAnswer(receiveData, scan);
                    System.out.println("=======================================");
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
                    System.out.println("Please enter ID of User u want to delete");

                    String deleteID;

                    do {
                        deleteID = scanner.nextLine();
                    } while (!checkInt(deleteID));

                    sendData.put(scan, tryToParse(deleteID));
                    socketSender.sender(sendData);

                    receiveData = socketSender.catcher();

                    if ((boolean) receiveData.get(scan)) {
                        System.out.printf("User with ID->%s deleted", deleteID);
                    } else {
                        System.out.printf("No such user with ID->%s", deleteID);
                    }
                    break;
            }
            socketSender.stopAllProcess();
        } else {
            System.out.println("Wrong command, pls use help");
        }
    }

    @SuppressWarnings("unchecked")
    private void printAnswer(HashMap<String, Object> receiveData, String cmd) {
        ArrayList<EntityUser> us = (ArrayList<EntityUser>) receiveData.get(cmd);
        if (!us.isEmpty()) {

            for (EntityUser user : us) {
                System.out.println(user.toString());
            }

        } else {
            System.out.println("Received data has no Users");
        }

    }

    public int tryToParse(String param) {
        int in = -1;
        try {
            in = Integer.parseInt(param);
        } catch (Exception e) {
            return in;
        }
        return in;
    }

    private boolean checkInt(String param) {
        if (tryToParse(param) == -1) {
            System.out.println("Wrong ID, ID must be integer and not negative");
            return false;
        } else {
            return true;
        }
    }
}
