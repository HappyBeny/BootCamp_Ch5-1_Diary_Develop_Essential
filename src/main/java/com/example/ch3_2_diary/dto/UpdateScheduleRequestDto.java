package com.example.ch3_2_diary.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    private final String schedule;
    private final String description;

    public UpdateScheduleRequestDto(String schedule, String description) {
        this.schedule = schedule;
        this.description = description;
    }
}
