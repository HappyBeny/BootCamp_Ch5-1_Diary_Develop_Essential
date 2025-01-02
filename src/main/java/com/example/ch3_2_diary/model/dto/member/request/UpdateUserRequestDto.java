package com.example.ch3_2_diary.model.dto.member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserRequestDto {

    private final String username;
    private final String oldPassword;
    private String newPassword;
}
