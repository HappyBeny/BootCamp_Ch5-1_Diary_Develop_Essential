package com.example.ch5_1_diary_essential;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ch5_1_Diary_EssentialApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch5_1_Diary_EssentialApplication.class, args);
    }

}
