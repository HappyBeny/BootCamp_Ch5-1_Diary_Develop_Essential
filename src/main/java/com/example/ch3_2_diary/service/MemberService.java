package com.example.ch3_2_diary.service;

import com.example.ch3_2_diary.dto.*;
import com.example.ch3_2_diary.entity.Member;
import com.example.ch3_2_diary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * @param requestDto
     */
    public MemberResponseDto signUp(CreateMemberRequestDto requestDto) {
        Member savedMember = memberRepository.save(
                new Member(requestDto.getUsername(),
                        requestDto.getPassword(),
                        requestDto.getEmail())
        );
        return new MemberResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getEmail());
    }

    /**
     * 로그인
     * @param requestDto
     */
    public MemberResponseDto login(LoginRequestDto requestDto) {
        Member member = memberRepository.findByEmailOrElseThrow(requestDto.getEmail());

        if (!member.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }

        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }



    /**
     * 회원 조회
     * @param id
     */
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findByIdOrElseThrow(id);
        return new MemberResponseDto(member.getId(), member.getUsername(), member.getEmail());
    }

    /**
     * 유저 정보 수정
     * @param id
     * @param requestDto
     */
    public MemberResponseDto updateUserInfo(Long id, UpdateUserRequestDto requestDto) {
        Member foundMember = validatePassword(id, requestDto.getPassword());

        foundMember.setUsername(requestDto.getUsername());
        foundMember.setPassword(requestDto.getPassword());

        Member updatedMember = memberRepository.save(foundMember);

        return new MemberResponseDto(
                updatedMember.getId(),
                updatedMember.getUsername(),
                updatedMember.getEmail()
        );
    }

    /**
     * 소프트 딜리트
     * @param id
     * @param requestDto
     */
    public void softDelete(Long id, DeleteMemberRequestDto requestDto) {
        Member foundMember = validatePassword(id, requestDto.getPassword());

        foundMember.setDeleted(true);
        memberRepository.save(foundMember);
    }

    /**
     * 비밀번호 검증 메서드
     * @param id
     * @param password
     */
    public Member validatePassword(Long id, String password) {
        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        if (!foundMember.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password");
        }
        return foundMember;
    }
}
