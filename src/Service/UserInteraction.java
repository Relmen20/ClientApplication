package Service;

import Supports.User;

import java.util.ArrayList;

public class UserInteraction {
    private ArrayList<User> users = new ArrayList<User>();

    public void setUsers(User user){
        this.users.add(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
