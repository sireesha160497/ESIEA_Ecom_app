package com.ecom.user;

public class User {
    private int id;
    private String name;
    private String password;

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public boolean login(String name, String password) {
        return this.name.equals(name) && this.password.equals(password);
    }
}
