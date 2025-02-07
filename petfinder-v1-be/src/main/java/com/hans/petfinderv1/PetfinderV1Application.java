package com.hans.petfinderv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PetfinderV1Application {

    public static void main(String[] args) {
        SpringApplication.run(PetfinderV1Application.class, args);
    }

}
