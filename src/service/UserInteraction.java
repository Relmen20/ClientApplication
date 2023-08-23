package service;

import entity.EntityUser;
import entity.Gender;

import java.util.Scanner;

public class UserInteraction {
    private Scanner scanner;

    public UserInteraction(Scanner scanner) {
        this.scanner = scanner;
    }


    public EntityUser createUser() {
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();


        Gender gender;
        do {
            System.out.print("Please enter your gender m/f: ");
            String inputGender = scanner.nextLine();
            gender = checkGender(inputGender);
        } while (gender == null);

        byte age;
        System.out.print("Please enter your age: ");
        do {
            age = scanner.nextByte();
        } while (checkAge(age));

        int ID = 1;

        return new EntityUser(name, age, ID, gender);
    }

    private boolean checkAge(byte age) {
        if (age > 0 && age <= 100) return false;
        else {
            System.out.println("Please set your age between 1 and 100");
            return true;
        }
    }

    private Gender checkGender(String inputGender) {
        Gender gender;
        if (inputGender.equals("male")) {
            gender = Gender.MALE;
            return gender;
        } else if (inputGender.equals("female")) {
            gender = Gender.FEMALE;
            return gender;
        } else {
            System.out.println("Please enter your gender by choose <<male>> if u r male\n" +
                    "Or by choose <<female>> if u r female");
            return null;
        }
    }
}
