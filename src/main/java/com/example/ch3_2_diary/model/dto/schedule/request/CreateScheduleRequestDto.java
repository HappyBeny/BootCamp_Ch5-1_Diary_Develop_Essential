package com.example.ch3_2_diary.model.dto.schedule.request;

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
