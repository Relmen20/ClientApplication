package model;

import java.util.HashMap;

public enum CommandList {
    HELP("help", "Enter the 'help' to see all commands with them descriptions"),
    CREATE("create", "To create new user"),
    READ("read", "Show user by ID"),
    READ_ALL("read all", "Command for read all users"),
    UPDATE("update", "Update information about user by ID"),
    DELETE("delete", "Delete user by ID"),
    DELETE_ALL("delete all", "Delete all users");
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

    static {
        for (CommandList command : CommandList.values()) {
            stringEnum.put(command.shortCommand, command);
        }
    }

    public static HashMap<String, CommandList> getStringEnum() {
        return stringEnum;
    }

    @Override
    public String toString() {
        return shortCommand + " -- " + description;
    }

    public void printHelp() {
        for (CommandList command : CommandList.values()) {
            System.out.println(command.toString());
        }
    }
}