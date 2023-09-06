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

        System.out.println("Enter the message");
        String scannerCommand = scanner.nextLine().toUpperCase(Locale.ROOT);
        try {
            CommandList commandList = CommandList.valueOf(scannerCommand);
            if(commandList == CommandList.EXIT){
                EXIT_FLAG = false;
                return;
            }
            SocketSender socketSender = new SocketSender();
            switch (commandList) {

                case HELP:
                    commandList.printHelp();
                    break;

                case READ:
                    System.out.println("Please enter an ID of user");
                    sendMapRequest.put(scannerCommand, tryToParse(scanner));
                    socketSender.sendRequest(sendMapRequest);

                    receivedMap = socketSender.catchRespond();
                    System.out.println(receivedMap.get(scannerCommand).toString());
                    break;

                case READ_ALL:
                    System.out.println("============== All users ==============");

                    sendMapRequest.put(scannerCommand, "all");
                    socketSender.sendRequest(sendMapRequest);

                    receivedMap = socketSender.catchRespond();
                    printAllUsers(receivedMap, scannerCommand);
                    System.out.println("=======================================");
                    break;

                case CREATE:
                    System.out.println("Create new user");
                    EntityUser user = userService.createUser(0);

                    sendMapRequest.put(scannerCommand, user);
                    socketSender.sendRequest(sendMapRequest);

                    receivedMap = socketSender.catchRespond();
                    System.out.println(receivedMap);
                    break;

                case DELETE:
                    System.out.println("Please enter ID of User u want to delete");
                    int deleteID = tryToParse(scanner);

                    sendMapRequest.put(scannerCommand, deleteID);
                    socketSender.sendRequest(sendMapRequest);
                    receivedMap = socketSender.catchRespond();

                    if ((boolean) receivedMap.get(scannerCommand)) {
                        System.out.printf("User with ID --> %s deleted\n", deleteID);
                    } else {
                        System.out.printf("No such user with ID->%s\n", deleteID);
                    }
                    break;

                case DELETE_ALL:
                    System.out.println("============= Delete users =============");

                    sendMapRequest.put(scannerCommand, "all");
                    socketSender.sendRequest(sendMapRequest);

                    receivedMap = socketSender.catchRespond();
                    printDeletedUsers(receivedMap, scannerCommand);
                    System.out.println("\n========================================");
                    break;

                case UPDATE:
                    System.out.println("Please enter ID of user u want to update");
                    sendMapRequest.put(CommandList.READ.name(), tryToParse(scanner));
                    socketSender.sendRequest(sendMapRequest);
                    receivedMap = socketSender.catchRespond();

                    EntityUser updateUser = userService.updateUser(receivedMap.get(CommandList.READ.name()));
                    sendMapRequest.clear();
                    sendMapRequest.put(scannerCommand, updateUser);
                    socketSender.sendRequest(sendMapRequest);
                    receivedMap = socketSender.catchRespond();
                    System.out.println(receivedMap.get(scannerCommand));
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
        ArrayList<Integer> arrayOfID = (ArrayList<Integer>) receiveData.get(cmd);
        if (!arrayOfID.isEmpty()) {
            for (Integer user : arrayOfID) {
                System.out.printf("\t  User with ID = %d --> deleted\n", user);
            }
            System.out.printf("   Total count of users deleted --> %s", arrayOfID.size());
        } else {
            System.out.print("\t   Received data has no Users");
        }
    }

    @SuppressWarnings("unchecked")
    private void printAllUsers(HashMap<String, Object> receiveData, String cmd) {
        ArrayList<EntityUser> userArrayList = (ArrayList<EntityUser>) receiveData.get(cmd);
        if (!userArrayList.isEmpty()) {
            for (EntityUser user : userArrayList) {
                System.out.println(user.toString());
            }
        } else {
            System.out.println("\t  Received data has no Users");
        }
    }

    private int tryToParse(Scanner scanner) {
        int inputID = -1;
        do {
            try {
                inputID = Integer.parseInt(scanner.nextLine());
                if (inputID <= 0) {
                    throw new RuntimeException();
                }
            }catch (NumberFormatException e){
                System.out.println("ID must be integer");
            }catch (RuntimeException e) {
                System.out.println("ID cant be 0 or less");
            }
        } while (inputID <= 0);
        return inputID;
    }

    public boolean isEXIT_FLAG() {
        return EXIT_FLAG;
    }
}