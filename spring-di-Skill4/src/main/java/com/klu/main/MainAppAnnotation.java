package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.klu.model.Student;
import com.klu.config.AppConfig;

public class MainAppAnnotation {
    public static void main(String[] args) {

        ApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

        Student student = (Student) context.getBean("student2");

        student.display();
    }
}
