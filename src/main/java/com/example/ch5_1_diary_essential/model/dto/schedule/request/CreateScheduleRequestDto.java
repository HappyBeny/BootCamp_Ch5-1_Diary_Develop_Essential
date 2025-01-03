package com.example.ch5_1_diary_essential.model.dto.schedule.request;

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
