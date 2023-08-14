package Supports;

import controller.CommandList;

import java.util.HashMap;
import java.util.Locale;

public enum Gender {
    MALE("male", "m"),
    FEMALE("female", "f");

    private String longGender;
    private String shortGender;

    Gender(String longGender, String shortGender){
        this.longGender = longGender;
        this.shortGender = shortGender;
    }

    public String getShortGender() {
        return shortGender;
    }

    public String getLongGender() {
        return longGender;
    }

}
