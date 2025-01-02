package com.example.ch3_2_diary.model.dto.member.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteMemberRequestDto {

    private final String password;
}
