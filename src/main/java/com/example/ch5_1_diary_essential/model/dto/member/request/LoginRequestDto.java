package com.example.ch5_1_diary_essential.model.dto.member.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "이메일 주소를 입력해주세요")
    @Email(message = "이메일 주소는 올바른 형식이어야 합니다.")
    private final String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private final String password;
}
