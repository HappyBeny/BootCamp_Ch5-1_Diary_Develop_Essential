package com.example.ch5_1_diary_essential.model.dto.member.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteMemberRequestDto {

    private final String password;
}
