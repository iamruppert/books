package com.lukasz.stephen_king;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StephenKingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StephenKingApplication.class, args);
    }

}
