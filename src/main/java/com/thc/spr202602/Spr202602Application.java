package com.thc.spr202602;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Spr202602Application {
    public static void main(String[] args) {
        SpringApplication.run(Spr202602Application.class, args);
    }
}
