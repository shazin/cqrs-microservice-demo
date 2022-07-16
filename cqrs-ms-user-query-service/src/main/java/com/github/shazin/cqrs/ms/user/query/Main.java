package com.github.shazin.cqrs.ms.user.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Main {

    public static void main(String... args) {
        SpringApplication.run(Main.class, args);
    }
}
