package com.medhatmhtt.dbtest;

/**
 * Created by SM on 2/18/2018.
 */

public class MovieAward {
    int ID;
    String Name,Type;

    public MovieAward(int ID, String name, String type) {
        this.ID = ID;
        Name = name;
        Type = type;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }
}
