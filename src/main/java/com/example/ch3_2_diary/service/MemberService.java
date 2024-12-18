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

    public MemberResponseDto findById(Long id) {

        Member member = memberRepository.findByIdOrElseThrow(id);

        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }

    public MemberResponseDto updateUserInfo(Long id, CreateMemberRequestDto requestDto) {
        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        foundMember.setUsername(requestDto.getUsername());
        foundMember.setEmail(requestDto.getEmail());

        Member updatedMember = memberRepository.save(foundMember);

        return new MemberResponseDto(
                updatedMember.getId(),
                updatedMember.getUsername(),
                updatedMember.getEmail()
        );
    }

    public void softDelete(Long id) {
        Member foundMember = memberRepository.findByIdOrElseThrow(id);
        foundMember.setDeleted(true);
        memberRepository.save(foundMember);
    }
}
