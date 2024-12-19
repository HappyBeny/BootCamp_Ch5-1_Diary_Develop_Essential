package com.example.ch3_2_diary.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final String schedule;
    private String description;

    public CreateScheduleRequestDto(String schedule, String description) {
        this.schedule = schedule;
        this.description = description;
    }
}
