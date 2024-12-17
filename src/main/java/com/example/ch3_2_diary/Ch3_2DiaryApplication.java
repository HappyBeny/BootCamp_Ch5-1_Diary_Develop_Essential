package com.example.ch3_2_diary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ch3_2DiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch3_2DiaryApplication.class, args);
    }

}
