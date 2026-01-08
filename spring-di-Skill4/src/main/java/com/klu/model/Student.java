package com.klu.model;

import org.springframework.stereotype.Component;

@Component("student2") // bean ID
public class Student {

    private int studentId;
    private String name;
    private String course;
    private int year;

    // Constructor
    public Student() {} // default required for setter injection

    // Setters (DI will use these)
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Display
    public void display() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Year: " + year);
    }
}
