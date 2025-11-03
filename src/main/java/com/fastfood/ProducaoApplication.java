package com.fastfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.fastfood"})
@EnableJpaRepositories
public class ProducaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducaoApplication.class, args);
    }
}

//testeworkflow