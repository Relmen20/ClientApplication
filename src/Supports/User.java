package Supports;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private byte age;
    private String password;
    private Gender gender;

    public User(){}

    public User(String name, byte age, String password, Gender gender){
        this.name = name;
        this.age = age;
        this.password = password;
        this.gender = gender;
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

    public Gender getGender() {
        return gender;
    }

    public String toString(){
        return "User data: " + name + " " + age + " " + gender.getLongGender();
    }

}
