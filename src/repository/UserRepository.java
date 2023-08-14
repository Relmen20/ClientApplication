package repository;

import Supports.User;

import java.io.*;

public class UserRepository {
    private static final String FILEPATH = "/home/andrew/IdeaProjects/NativeCSR/repo/src/data/";

    public static void objectToFile(User obj) {

        File file = new File(FILEPATH);
        String[] ls = file.list();
        int fileCounter = ls == null ? 0 : ls.length;

        try {
            String fullFileName = "Object_" + fileCounter + ".ser";
            FileOutputStream fileOut = new FileOutputStream(new File(FILEPATH, fullFileName));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            System.out.printf("Object %s was saved successfully\n", obj.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static User getObject(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            User user = (User) objectIn.readObject();

            objectIn.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFILEPATH() {
        return FILEPATH;
    }
}
