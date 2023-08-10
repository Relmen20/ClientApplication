package Service;

import Controller.CommandList;
import Controller.ScannerValidation;

import static Controller.CommandList.getStringEnum;

public class Commands {
    private ScannerValidation userScanner;
    private UserInteraction users;
    public Commands(ScannerValidation userScanner){
        this.userScanner = userScanner;
    }

    public void ComandHandler(){

        System.out.println("Enter the message");
        String scan = userScanner.nextLine();
        CommandList commandList = getStringEnum().get(scan);
        if(commandList != null) {
            switch (commandList) {
                case HELP:
                    //TODO: move to enum
                    commandList.printHelp();
                    break;
                case ALLUSERS:
                    System.out.println("ALL USERS");
                    break;
                case REGISTRATION:
                    System.out.println("Reg new user");
                    Registration reg = new Registration();
                    reg.getNewUser(userScanner);
                    break;
                case REMOVE:
                    System.out.println("Why do u want do this...");
                    break;
                default:
                    System.out.println("Wrong command, pls use help");
            }
        }else{
            System.out.println("Wrong command, pls use help");
        }
    }
}
