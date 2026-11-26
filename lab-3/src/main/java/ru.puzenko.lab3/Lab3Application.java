package ru.puzenko.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab3Application {
    public static void main(String[] args) {
        // Эта строка решает проблему с Java 27
        System.setProperty("spring.classformat.ignore", "true");
        SpringApplication.run(Lab3Application.class, args);
    }
}