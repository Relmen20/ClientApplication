import service.CommandService;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandService commandService = new CommandService(scanner);
        while (commandService.isEXIT_FLAG()) {
            commandService.process();
        }
    }
}