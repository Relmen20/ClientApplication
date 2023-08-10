package Controller;

import java.util.HashMap;

public enum CommandList {
    HELP("help", "Enter the 'help' to see all commands with them descriptions"),
    REGISTRATION("reg", "To registration new user"),
    ALLUSERS("sau", "See all users"),
    REMOVE("kill", "Remove user");
    private String shortCommand;
    private String description;
    CommandList(String shortCommand, String description) {
        this.shortCommand = shortCommand;
        this.description = description;

    }

    public String getShortCommand() {
        return shortCommand;
    }

    public String getDescription() {
        return description;
    }

    private static HashMap<String, CommandList> stringEnum = new HashMap<String, CommandList>();

    static{
        for(CommandList command: CommandList.values()){
            stringEnum.put(command.shortCommand, command);
        }
    }

    public static HashMap<String, CommandList> getStringEnum() {
        return stringEnum;
    }

    @Override
    public String toString(){
        return shortCommand + " -- " + description;
    }

    public void printHelp(){
        for (CommandList command : CommandList.values()) {
            System.out.println(command.toString());
        }
    }
}