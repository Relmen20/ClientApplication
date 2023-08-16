package service;

import controller.CommandList;
import controller.UserScanner;

import static controller.CommandList.getStringEnum;

public class Commands {
    private UserScanner userScanner = new UserScanner();
    private UserInteraction userInteraction = new UserInteraction(userScanner);

    public void comandHandler(){

        System.out.println("Enter the message");
        String scan = userScanner.nextLine();
        CommandList commandList = getStringEnum().get(scan);
        if(commandList != null) {
            switch (commandList) {
                case HELP:
                    commandList.printHelp();
                    break;
                case READ:
//                    userInteraction.getUsers();
//                    userInteraction.printUsers();
                    break;
                case CREATE:
                    System.out.println("Reg new user");
                    userInteraction.createUser();
                    break;
                case DELETE:
//                    userInteraction.removeUsers();
                    break;
                default:
                    System.out.println("Wrong command, pls use help");
            }
        }else{
            System.out.println("Wrong command, pls use help");
        }
    }
}
