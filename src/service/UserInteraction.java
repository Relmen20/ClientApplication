package service;

import controller.UserScanner;
import repository.UserRepository;
import supports.Gender;
import supports.EntityUser;

import java.io.*;
import java.util.ArrayList;

public class UserInteraction {
    private ArrayList<EntityUser> entityUsers = new ArrayList<>();

    private int actualID;
    private final static String actualIDFilePath = "/home/andrew/IdeaProjects/NativeCSR/repo/src/data/Object_0.ser";
    private UserRepository userRepository = new UserRepository();
    private UserScanner scanner;

    public UserInteraction(UserScanner userScanner) {
        this.scanner = userScanner;
        this.actualID = getActualIDFromFile();
    }

    public void setUsers(EntityUser entityUser) {
        this.entityUsers.add(entityUser);
    }

//    public ArrayList<User> getUsers() {
//
//        String filePath = userRepository.getFILEPATH();
//
//        int fileCounter = countOfUsers(filePath);
//
//        if (fileCounter > users.size()) {
//            User user;
//            String fullFileName;
//            for (int i = users.size(); i < fileCounter; i++) {
//                fullFileName = filePath + "Object_" + i + ".ser";
//                user = userRepository.getObject(fullFileName);
//                if (user != null) setUsers(user);
//            }
//        }
//        return users;
//    }

//    public void removeUsers() {
//        String filePath = userRepository.getFILEPATH();
//        int fileCounter = countOfUsers(filePath);
//        if (users.isEmpty() && fileCounter == 0) {
//            System.out.println("There is no users to remove");
//        } else {
//            users.clear();
//
//            if (fileCounter != 0) {
//                File file;
//                System.out.println("Count of users deleted: " + fileCounter);
//                for (int i = 1; i < fileCounter; i++) {
//                    file = new File(filePath + "Object_" + i + ".ser");
//                    file.delete();
//                }
//            }
//        }
//    }

    public void createUser() {
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();


        Gender gender;
        do {
            System.out.print("Please enter your gender m/f: ");
            String inputGender = scanner.nextLine();
            gender = checkGender(inputGender);
        } while (gender == null);

        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        byte age;
        System.out.print("Please enter your age: ");
        do {
            age = scanner.nextByte();
        } while (checkAge(age));

        EntityUser entityUser = new EntityUser(name, age, password, gender);
        userRepository.objectToFile(entityUser);
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

    public void printUsers() {
        entityUsers.forEach((entityUser) -> System.out.println(entityUser.toString()));
    }

    public int countOfUsers(String filePath) {
        File file = new File(filePath);
        String[] ls = file.list();
        return ls == null ? 0 : ls.length - 1;
    }

    public int getActualIDFromFile() {
        int ID;
        try {
            FileInputStream fileIn = new FileInputStream(actualIDFilePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            ID = (int) objectIn.readObject();
            objectIn.close();
        } catch (Exception e) {
            ID = 1;
            createFileActualID(ID);
        }

        return ID;
    }

    public void createFileActualID(int actualID) {
        try {
            String fileName = "Object_0.ser";
            FileOutputStream fileOut = new FileOutputStream(new File(userRepository.getFILEPATH(), fileName));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(actualID);
            objectOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setActualIDToFile(int ID){
        try {
            PrintWriter writer = new PrintWriter(actualIDFilePath);
            writer.print("");
            writer.print(ID);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
