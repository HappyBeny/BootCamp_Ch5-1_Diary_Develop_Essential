package com.example.ch5_1_diary_essential.model.dto.schedule.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateScheduleRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotEmpty(message = "일정 이름을 입력해주세요.")
    @Size(max = 30, message = "일정 이름은 30자를 초과할 수 없습니다.")
    private String schedule;

    private String description;
}
