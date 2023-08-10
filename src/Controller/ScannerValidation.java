package Controller;

import java.util.Scanner;

public class ScannerValidation {
    private Scanner scanner;

    public ScannerValidation(Scanner scanner) {
        this.scanner = scanner;
    }

    public String nextLine() {
        return this.scanner.nextLine();
    }

    public int nextInt(){
        return this.scanner.nextInt();
    }
}