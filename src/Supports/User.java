package Supports;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private int age;
    private String password;

    public User(String name, int age, String password){
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }
}
