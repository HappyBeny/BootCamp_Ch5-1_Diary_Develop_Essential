package com.example.ch5_1_diary_essential.model.dto.member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserRequestDto {

    private final String username;
    private final String oldPassword;
    private String newPassword;
}
