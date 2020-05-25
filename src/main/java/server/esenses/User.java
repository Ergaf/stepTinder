package server.esenses;

import java.util.Objects;

public class User {
    int id;
    String name;
    String pass;
    String photo;

    public User(int id, String name, String pass, String photo) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.photo = photo;
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

