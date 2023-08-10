package Controller;

import java.io.*;

public class FileController {
    private static final String filepath = "/home/andrew/IdeaProjects/NativeCSR/src/data/";
    private static int fileCounter = 0;

    public static void ObjectToFile(Object obj) {
        if (fileCounter == 0) {
            File file = new File(filepath);
            String[] ls = file.list();
            fileCounter = ls == null ? 0 : ls.length;
            System.out.println(fileCounter);
        }
        try {
            String fullFileName = "Object_" + fileCounter + ".txt";
            FileOutputStream fileOut = new FileOutputStream(new File(filepath, fullFileName));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();
            System.out.printf("Object %s was saved successfully\n", obj.getClass());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        fileCounter++;
    }

    public static Object GetObject(String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            String noObject = "there is no object";
            return noObject;
        }
    }

}
