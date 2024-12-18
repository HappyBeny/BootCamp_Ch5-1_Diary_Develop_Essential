package com.example.ch3_2_diary.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMemberRequestDto {

    private final String username;
    private final String password;
    private final String email;
}
