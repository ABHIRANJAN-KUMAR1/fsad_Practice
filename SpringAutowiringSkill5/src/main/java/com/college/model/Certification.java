package com.college.model;

import org.springframework.stereotype.Component;

@Component   
public class Certification {

    private int id;
    private String name;
    private String dateOfCompletion;

    public Certification() {
        this.id = 101;
        this.name = "Spring Certification";
        this.dateOfCompletion = "15-Dec-2025";
    }

    @Override
    public String toString() {
        return "Certification [id=" + id + ", name=" + name +
                ", dateOfCompletion=" + dateOfCompletion + "]";
    }
}
