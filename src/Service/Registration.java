package Service;

import Controller.FileController;
import Controller.ScannerValidation;
import Supports.User;

public class Registration {

    private User user;

    public void getNewUser(ScannerValidation scanner) {
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.print("\nPlease enter your age: ");
        //TODO create validation for age
        int age = scanner.nextInt();
        System.out.print("\nPlease enter your password: ");
        String password = scanner.nextLine();

        this.user = new User(name, age, password);
        FileController.ObjectToFile(user);
    }
}
