package com.example.tailwebsapp.model;

import io.realm.RealmObject;

public class studentDetails extends RealmObject {
    public static final String ID = "id";
    private String id, name, subject;

    private float marks;
    public studentDetails (){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
