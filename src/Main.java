import Controller.ScannerValidation;
import Service.Commands;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ScannerValidation sc = new ScannerValidation(scanner);
        Commands commands = new Commands(sc);
        while(true){
            commands.ComandHandler();
        }
    }
}