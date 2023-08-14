package controller;

import java.util.Scanner;

public class ScannerValidation {
    private Scanner scanner = new Scanner(System.in);

    public String nextLine() {
        return this.scanner.nextLine();
    }

    public int nextInt(){
        return this.scanner.nextInt();
    }

    public byte nextByte(){
        return this.scanner.nextByte();
    }
}