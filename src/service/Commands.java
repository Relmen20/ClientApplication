package service;

import controller.CommandList;
import controller.ScannerValidation;

import static controller.CommandList.getStringEnum;

public class Commands {
    private ScannerValidation userScanner = new ScannerValidation();
    private UserInteraction users = new UserInteraction();


    public void comandHandler(){

        System.out.println("Enter the message");
        String scan = userScanner.nextLine();
        CommandList commandList = getStringEnum().get(scan);
        if(commandList != null) {
            switch (commandList) {
                case HELP:
                    commandList.printHelp();
                    break;
                case ALLUSERS:
                    users.getUsers();
                    users.printUsers();
                    break;
                case REGISTRATION:
                    System.out.println("Reg new user");
                    Registration reg = new Registration();
                    reg.getNewUser(userScanner);
                    break;
                case REMOVE:
                    users.removeUsers();
                    break;
                default:
                    System.out.println("Wrong command, pls use help");
            }
        }else{
            System.out.println("Wrong command, pls use help");
        }
    }
}
