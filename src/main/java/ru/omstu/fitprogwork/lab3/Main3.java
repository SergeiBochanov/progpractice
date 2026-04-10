package ru.omstu.fitprogwork.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Main3 {
    static void main(String[] args) { SpringApplication.run(Main3.class, args); }
}
