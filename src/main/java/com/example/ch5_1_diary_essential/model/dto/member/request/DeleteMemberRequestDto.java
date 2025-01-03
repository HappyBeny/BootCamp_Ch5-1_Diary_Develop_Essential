package com.example.ch5_1_diary_essential.model.dto.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteMemberRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
}
