package com.example.ch3_2_diary.dto;

import lombok.Getter;

@Getter
public class CreateMemberRequestDto {

    private final String username;
    private final String email;

    public CreateMemberRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
