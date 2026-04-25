package ru.omstu.fitprogwork.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.omstu.fitprogwork.lab3", "ru.omstu.fitprogwork.db"})
public class Main3 {
    static void main(String[] args) { SpringApplication.run(Main3.class, args); }
}