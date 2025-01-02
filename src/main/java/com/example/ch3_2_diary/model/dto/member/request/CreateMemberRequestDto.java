package com.example.ch3_2_diary.model.dto.member.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMemberRequestDto {

    private final String email;
    private final String username;
    private final String password;
}
