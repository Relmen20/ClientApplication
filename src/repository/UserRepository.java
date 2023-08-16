package repository;

import supports.EntityUser;

import java.io.*;

public class UserRepository {
    private static final String FILEPATH = "/home/andrew/IdeaProjects/NativeCSR/repo/src/data/";

    public void objectToFile(EntityUser obj) {

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

    public EntityUser getObject(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            EntityUser entityUser = (EntityUser) objectIn.readObject();

            objectIn.close();
            return entityUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFILEPATH() {
        return FILEPATH;
    }
}
