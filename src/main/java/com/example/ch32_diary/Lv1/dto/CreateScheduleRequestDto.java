package com.example.ch32_diary.Lv1.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final String writer;
    private final String schedule;
    private String description;

    public CreateScheduleRequestDto(String writer, String schedule, String description) {
        this.writer = writer;
        this.schedule = schedule;
        this.description = description;
    }
}
