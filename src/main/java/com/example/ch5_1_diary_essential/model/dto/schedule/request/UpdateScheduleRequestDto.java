package com.example.ch5_1_diary_essential.model.dto.schedule.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateScheduleRequestDto {

    private final String password;
    private final String schedule;
    private final String description;
}
