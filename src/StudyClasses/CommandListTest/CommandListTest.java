package StudyClasses.CommandListTest;


import java.util.Locale;
import java.util.Scanner;

public enum CommandListTest {
    HELP("Enter the 'help' to see all commands with them descriptions"),
    CREATE("To create new user"),
    READ("Show user by ID"),
    READ_ALL("Command for read all users"),
    UPDATE("Update information about user by ID"),
    DELETE("Delete user by ID"),
    DELETE_ALL("Delete all users");
    private final String description;

    CommandListTest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name() + " -- " + description;
    }

    public void printHelp() {
        for (CommandListTest command : CommandListTest.values()) {
            System.out.println(command.toString());
        }
    }

    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);

            String command = scanner.nextLine().toUpperCase(Locale.ROOT);
            try {
                CommandListTest list = CommandListTest.valueOf(command);


                switch (list) {
                    case HELP:
                    case READ:
                    case CREATE:
                    case DELETE:
                    case UPDATE:
                    case READ_ALL:
                    case DELETE_ALL:
                        System.out.println(list.getDescription());
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("wrong command, try again");
            }
        }
    }

}