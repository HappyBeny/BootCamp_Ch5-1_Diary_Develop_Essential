package com.example.ch3_2_diary.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserRequestDto {

    private final String username;
    private final String password;
}
