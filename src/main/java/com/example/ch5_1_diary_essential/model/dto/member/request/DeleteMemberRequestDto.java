package com.example.ch5_1_diary_essential.model.dto.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteMemberRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요")
    private final String password;
}
