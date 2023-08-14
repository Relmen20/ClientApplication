import controller.ScannerValidation;
import service.Commands;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Commands commands = new Commands();
        while(true){
            commands.comandHandler();
        }
    }
}