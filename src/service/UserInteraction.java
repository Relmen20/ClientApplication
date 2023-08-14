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
        File file = new File(filePath);
        String[] ls = file.list();

        int fileCounter = ls == null ? 0 : ls.length;

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

    public void printUsers(){
        users.forEach((user) -> System.out.println(user.toString()));
    }
}
