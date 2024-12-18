package com.example.ch3_2_diary.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final Long memberId;
    private final String schedule;
    private String description;

    public CreateScheduleRequestDto(Long memberId, String schedule, String description) {
        this.memberId = memberId;
        this.schedule = schedule;
        this.description = description;
    }
}
