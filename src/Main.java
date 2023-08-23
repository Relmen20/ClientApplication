import service.Commands;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Commands commands = new Commands(scanner);
        while (true) {
            commands.comandHandler();
        }
    }
}