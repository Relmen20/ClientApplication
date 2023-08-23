package entity;

import java.io.Serializable;

public class EntityUser implements Serializable {

    private static final long serialVersionUID = 12345678910L;
    private String name;
    private byte age;
    private int ID;
    private Gender gender;

    public EntityUser() {
    }

    public EntityUser(String name, byte age, int ID, Gender gender) {
        this.name = name;
        this.age = age;
        this.ID = ID;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getRole() {
        return ID;
    }

    public Gender getGender() {
        return gender;
    }

    public String toString() {
        return "User data: " + name + " " + age + " " + gender.getLongGender();
    }

}
