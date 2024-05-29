package org.example;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class User {
    @Value("${student.name}")
    private String firstName;
    @Value("${student.last_name}")
    private String lastName;
    @Value("${student.group}")
    private String group;

    @PostConstruct
    public void print() {
        System.out.println(firstName + " " + lastName + " " + group);
    }
}