package com.example.ch3_2_diary.service;

import com.example.ch3_2_diary.dto.CreateMemberRequestDto;
import com.example.ch3_2_diary.dto.MemberResponseDto;
import com.example.ch3_2_diary.entity.Member;
import com.example.ch3_2_diary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto signUp(CreateMemberRequestDto requestDto) {

        Member member = new Member(requestDto.getUsername(), requestDto.getEmail());
        Member savedMember = memberRepository.save(member);

        return new MemberResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getEmail());
    }
}
