package com.example.ch5_1_diary_essential.model.dto.schedule.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteScheduleRequestDto {

    private final String password;
}
