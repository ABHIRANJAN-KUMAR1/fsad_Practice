package com.klu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.klu.model.Student;

@Configuration
public class AppConfig {

    @Bean("student2")
    public Student student() {
        Student s = new Student();
        s.setStudentId(1002);
        s.setName("Riya");
        s.setCourse("Mechanical");
        s.setYear(2026);
        return s;
    }
}
