package com.example.ch5_1_diary_essential.model.dto.member.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateMemberRequestDto {

    @NotBlank(message = "이메일값이 포함되어야 합니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private String email;

    @NotBlank(message = "사용자 이름이 포함되어야 합니다.")
    @Size(max = 12, message = "이름은 12자 이하여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호가 포함되어야 합니다.")
    @Size(max = 20, message = "비밀번호는 20자를 초과할 수 없습니다.")
    private String password;
}
