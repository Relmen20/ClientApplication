package service;

import Supports.User;
import controller.CommandList;
import repository.UserRepository;

import java.io.File;
import java.util.ArrayList;

public class UserInteraction {
    private ArrayList<User> users = new ArrayList<User>();

    public void setUsers(User user){
        this.users.add(user);
    }

    public ArrayList<User> getUsers() {

        String filePath = UserRepository.getFILEPATH();

        int fileCounter = countOfUsers(filePath);

        if(fileCounter > users.size()) {
            User user;
            String fullFileName;
            for (int i = users.size(); i < fileCounter; i++) {
                fullFileName = filePath + "Object_" + i + ".ser";
                user = UserRepository.getObject(fullFileName);
                if (user != null) setUsers(user);
            }
        }
        return users;
    }

    public void removeUsers(){
        String filePath = UserRepository.getFILEPATH();
        int fileCounter = countOfUsers(filePath);
        if(users.isEmpty() && fileCounter == 0){
            System.out.println("There is no users to remove");
        }else{
            users.clear();

            if(fileCounter != 0){
                File file;
                System.out.println("Count of users deleted: " + fileCounter);
                for(int i = 0; i < fileCounter; i++){
                    file = new File(filePath + "Object_" + i + ".ser");
                }
            }
        }
    }

    public void printUsers(){
        users.forEach((user) -> System.out.println(user.toString()));
    }

    public int countOfUsers(String filePath){
        File file = new File(filePath);
        String[] ls = file.list();
        return ls == null ? 0 : ls.length;
    }
}
