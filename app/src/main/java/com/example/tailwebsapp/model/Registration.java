package com.example.tailwebsapp.model;

import io.realm.RealmObject;

public class Registration extends RealmObject {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASS = "pass";
    private String  name, email, pass;
    private int id;


    public Registration(){

    }
    public Registration(int id, String name, String email, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
