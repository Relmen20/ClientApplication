import service.CommandService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandService commandService = new CommandService(scanner);
        while (commandService.isEXIT_FLAG()) {
            commandService.process();
        }
    }
}