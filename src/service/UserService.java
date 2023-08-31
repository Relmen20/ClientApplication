package service;

import model.EntityUser;
import model.Gender;

import java.util.Scanner;

public class UserService {
    private final Scanner scanner;

    public UserService(Scanner scanner) {
        this.scanner = scanner;
    }


    public EntityUser createUser(int ID) {
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();


        Gender gender;
        do {
            System.out.print("Please enter your gender m/f: ");
            String inputGender = scanner.nextLine();
            gender = checkGender(inputGender);
        } while (gender == null);

        String age;
        System.out.print("Please enter your age: ");
        do {
            age = scanner.nextLine();
        } while (checkAge(age) == 0);

        return new EntityUser(name, tryToByte(age), ID, gender);
    }

    private byte tryToByte(String age) {
        byte intAge = -1;

        try {
            intAge = Byte.parseByte(age);
        } catch (Exception e) {
            System.out.println(e);
        }

        return intAge;
    }

    private byte checkAge(String str) {
        byte age = tryToByte(str);
        if (age > 0 && age <= 100) return age;
        else {
            System.out.print("Please set your age between 1 and 100 --> ");
            return 0;
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
            System.out.print("Please enter your gender 'male / female' --> ");
            return null;
        }
    }

    public EntityUser updateUser(Object o) {

        EntityUser oldUser = (EntityUser) o;

        System.out.printf("U are going to change '%s' data\n" +
                          "==================================================\n" +
                          "Enter new name of user, current name '%s' -> ", oldUser.getName(), oldUser.getName());
        String name = scanner.nextLine();

        System.out.printf("Enter new gender of user, current gender '%s' -> ", oldUser.getGender());
        Gender gender;
        do {
            String inputGender = scanner.nextLine();
            gender = checkGender(inputGender);
        } while (gender == null);

        String age;
        System.out.printf("Enter new age of user, current age '%s' -> ", oldUser.getAge());
        do {
            age = scanner.nextLine();
        } while (checkAge(age) == 0);

        return new EntityUser(name, tryToByte(age), oldUser.getID(), gender);
    }
}
