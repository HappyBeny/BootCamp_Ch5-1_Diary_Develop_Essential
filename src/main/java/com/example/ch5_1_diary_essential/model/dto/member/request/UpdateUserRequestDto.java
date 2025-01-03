package com.example.ch5_1_diary_essential.model.dto.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserRequestDto {

    @NotBlank(message = "사용자 이름이 포함되어야 합니다.")
    @Size(max = 12, message = "이름은 12자 이하여야 합니다.")
    private final String username;

    @NotBlank(message = "기존 비밀번호를 입력해주세요")
    private final String oldPassword;

    @NotBlank(message = "신규 비밀번호가 포함되어야 합니다.")
    @Size(max = 20, message = "비밀번호는 20자를 초과할 수 없습니다.")
    private String newPassword;
}
