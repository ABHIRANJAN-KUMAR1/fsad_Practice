package com.klu.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.klu.model.Student;

public class MainAppXML {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("student-bean.xml");

        Student student = (Student) context.getBean("student1");

        student.display();
    }
}
