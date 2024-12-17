package com.example.ch3_2_diary.dto;

import com.example.ch3_2_diary.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long id;
    private final String username;
    private final String email;

    public MemberResponseDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }
}
