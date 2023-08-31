package service;

import controller.SocketSender;
import model.CommandList;
import model.EntityUser;

import java.util.*;

public class CommandService {
    private boolean EXIT_FLAG = true;
    private final Scanner scanner;
    private final UserService userService;

    public CommandService(Scanner scanner) {
        this.scanner = scanner;
        this.userService = new UserService(scanner);
    }

    public void process() {

        HashMap<String, Object> sendMapRequest = new HashMap<>();

        HashMap<String, Object> receivedMap;

        SocketSender socketSender;

        System.out.println("Enter the message");

        String scan = scanner.nextLine().toUpperCase(Locale.ROOT);

        try {
            CommandList commandList = CommandList.valueOf(scan);
            socketSender = new SocketSender();
            switch (commandList) {

                case HELP:
                    commandList.printHelp();
                    break;

                case READ:
                    System.out.println("Please enter an ID of user");

                    int readParam = tryToParse(scanner);

                    sendMapRequest.put(scan, readParam);
                    socketSender.sendRequest(sendMapRequest);

                    receivedMap = socketSender.catchRespond();
                    System.out.println(receivedMap.get(scan).toString());
                    break;

                case READ_ALL:
                    System.out.println("============== All users ==============");

                    sendMapRequest.put(scan, "all");
                    socketSender.sendRequest(sendMapRequest);

                    receivedMap = socketSender.catchRespond();
                    printAllUsers(receivedMap, scan);
                    System.out.println("=======================================");
                    break;

                case CREATE:

                    System.out.println("Create new user");
                    EntityUser user = userService.createUser(0);

                    sendMapRequest.put(scan, user);
                    socketSender.sendRequest(sendMapRequest);

                    receivedMap = socketSender.catchRespond();
                    System.out.println(receivedMap);
                    break;

                case DELETE:
                    System.out.println("Please enter ID of User u want to delete");

                    int deleteID = tryToParse(scanner);

                    sendMapRequest.put(scan, deleteID);
                    socketSender.sendRequest(sendMapRequest);

                    receivedMap = socketSender.catchRespond();

                    if ((boolean) receivedMap.get(scan)) {
                        System.out.printf("User with ID --> %s deleted\n", deleteID);
                    } else {
                        System.out.printf("No such user with ID->%s\n", deleteID);
                    }
                    break;

                case DELETE_ALL:

                    System.out.println("============= Delete users =============");

                    sendMapRequest.put(scan, "all");
                    socketSender.sendRequest(sendMapRequest);

                    receivedMap = socketSender.catchRespond();
                    printDeletedUsers(receivedMap, scan);
                    System.out.println("\n========================================");

                    break;

                case UPDATE:
                    int updateID = tryToParse(scanner);
                    System.out.println("Please enter ID of user u want to update");


                    EntityUser updateUser = userService.createUser(updateID);

                    sendMapRequest.put(scan, updateUser);
                    socketSender.sendRequest(sendMapRequest);

                    receivedMap = socketSender.catchRespond();

                    System.out.println(receivedMap.get(scan));
                    break;
                case EXIT:
                    EXIT_FLAG = false;
                    break;
            }
            socketSender.stopAllProcess();
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong command, pls use help");
        } catch (RuntimeException e) {
            System.out.println("Error, connection to server is failed");
        }
    }

    @SuppressWarnings("unchecked")
    private void printDeletedUsers(HashMap<String, Object> receiveData, String cmd) {
        ArrayList<Integer> us = (ArrayList<Integer>) receiveData.get(cmd);
        if (!us.isEmpty()) {

            for (Integer user : us) {
                System.out.printf("User with ID --> %d deleted\n", user);
            }

            System.out.printf("Total count of users deleted: %s", us.size());

        } else {
            System.out.print("\t   Received data has no Users");
        }

    }

    @SuppressWarnings("unchecked")
    private void printAllUsers(HashMap<String, Object> receiveData, String cmd) {
        ArrayList<EntityUser> us = (ArrayList<EntityUser>) receiveData.get(cmd);
        if (!us.isEmpty()) {
            for (EntityUser user : us) {
                System.out.println(user.toString());
            }
        } else {
            System.out.println("\t  Received data has no Users");
        }
    }

    private int tryToParse(Scanner scanner) {
        int in = -1;

        do {
            try {
                in = Integer.parseInt(scanner.nextLine());
                if (in <= 0) {
                    System.out.println("ID cant be 0 or less");
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                System.out.println("ID must be integer");
            }
        } while (in <= 0);

        return in;
    }

    public boolean isEXIT_FLAG() {
        return EXIT_FLAG;
    }
}
