package com.example.ch5_1_diary_essential.service;

import com.example.ch5_1_diary_essential.model.dto.member.request.CreateMemberRequestDto;
import com.example.ch5_1_diary_essential.model.dto.member.request.DeleteMemberRequestDto;
import com.example.ch5_1_diary_essential.model.dto.member.request.LoginRequestDto;
import com.example.ch5_1_diary_essential.model.dto.member.request.UpdateUserRequestDto;
import com.example.ch5_1_diary_essential.model.dto.member.response.MemberResponseDto;
import com.example.ch5_1_diary_essential.model.entity.Member;
import com.example.ch5_1_diary_essential.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
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
    public MemberResponseDto updateUserInfo(
            HttpSession session, Long id, UpdateUserRequestDto requestDto) {
        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        validateOwnerAndPassword(session, foundMember, requestDto.getOldPassword());

        foundMember.setUsername(requestDto.getUsername());
        if (requestDto.getNewPassword() != null) {
            foundMember.setPassword(requestDto.getNewPassword());
        }

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
    public void softDelete(HttpSession session, Long id, DeleteMemberRequestDto requestDto) {
        Member foundMember = memberRepository.findByIdOrElseThrow(id);

        validateOwnerAndPassword(session, foundMember, requestDto.getPassword());

        foundMember.setDeleted(true);
        memberRepository.save(foundMember);
    }

    /**
     * 비밀번호 검증 메서드
     * @param id
     * @param password
     */
    public void validateOwnerAndPassword(HttpSession session, Member member, String password){
        String loggedPassword = (String) session.getAttribute("password");
        String loggedUsername = (String) session.getAttribute("username");

        if (!member.getUsername().equals(loggedUsername)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not the owner of this account");
        }

        if (!loggedPassword.equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect Password");
        }
    }
}
