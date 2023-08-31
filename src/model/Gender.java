package model;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String longGender;

    Gender(String longGender) {
        this.longGender = longGender;
    }

    public String getLongGender() {
        return longGender;
    }

}
