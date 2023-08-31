package model;


import java.util.Locale;

public enum CommandList {
    HELP("Enter the 'help' to see all commands with them descriptions"),
    CREATE("To create new user"),
    READ("Show user by ID"),
    READ_ALL("Command for read all users"),
    UPDATE("Update information about user by ID"),
    DELETE("Delete user by ID"),
    DELETE_ALL("Delete all users"),
    EXIT("Exit from the application");
    private final String description;

    CommandList(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.ROOT) + " -- " + description;
    }

    public void printHelp() {
        for (CommandList command : CommandList.values()) {
            System.out.println(command.toString());
        }
    }

}