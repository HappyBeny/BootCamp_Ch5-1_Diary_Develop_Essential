package com.example.ch32_diary.Lv2.controller;

import com.example.ch32_diary.Lv2.dto.CreateMemberRequestDto;
import com.example.ch32_diary.Lv2.dto.MemberResponseDto;
import com.example.ch32_diary.Lv2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/members")
@RequiredArgsConstructor
public class MemberControllerLv2 {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signUp(CreateMemberRequestDto requestDto) {

        MemberResponseDto newMember = memberService.signUp(requestDto);

        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }
}
