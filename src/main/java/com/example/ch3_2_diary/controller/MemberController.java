package com.example.ch3_2_diary.controller;

import com.example.ch3_2_diary.dto.CreateMemberRequestDto;
import com.example.ch3_2_diary.dto.MemberResponseDto;
import com.example.ch3_2_diary.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signUp(CreateMemberRequestDto requestDto) {

        MemberResponseDto newMember = memberService.signUp(requestDto);

        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {

        MemberResponseDto responseDto = memberService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateUsername(
            @PathVariable Long id,
            @RequestBody CreateMemberRequestDto requestDto
    ) {
        MemberResponseDto responseDto = memberService.updateUsername(id, requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
