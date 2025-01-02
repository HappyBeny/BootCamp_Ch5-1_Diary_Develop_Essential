package com.example.ch3_2_diary.model.dto.schedule.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteScheduleRequestDto {

    private final String password;
}
