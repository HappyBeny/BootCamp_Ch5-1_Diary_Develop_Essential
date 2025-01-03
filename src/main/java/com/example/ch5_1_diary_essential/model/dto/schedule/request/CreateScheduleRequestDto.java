package com.example.ch5_1_diary_essential.model.dto.schedule.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateScheduleRequestDto {

    @NotEmpty
    @Size(max = 30, message = "일정 이름은 30자를 초과할 수 없습니다.")
    private String schedule;

    private String description;
}
