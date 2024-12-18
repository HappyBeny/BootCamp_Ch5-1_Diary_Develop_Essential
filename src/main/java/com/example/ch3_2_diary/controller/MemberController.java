package com.example.ch3_2_diary.controller;

import com.example.ch3_2_diary.dto.*;
import com.example.ch3_2_diary.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    /**
     * 회원가입
     * @param requestDto
     */
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signUp(@RequestBody CreateMemberRequestDto requestDto) {

        MemberResponseDto newMember = memberService.signUp(requestDto);

        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    /**
     * 로그인
     * @param requestDto
     * @param request
     * @param response
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequestDto requestDto,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 로그인 처리
        MemberResponseDto member = memberService.login(requestDto);

        // 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute("username", member.getUsername());

        // 쿠키에 세션 ID 저장
        Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
        sessionCookie.setPath("/");
        sessionCookie.setHttpOnly(true); // 보안 강화를 위해
        response.addCookie(sessionCookie);

        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    /**
     * 로그아웃
     * @param request
     * @param response
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest request,
            HttpServletResponse response) {

        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 쿠키 삭제
        Cookie sessionCookie = new Cookie("JSESSIONID", null);
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(0);
        response.addCookie(sessionCookie);

        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }

    /**
     * 회원 조회
     * @param id
     */
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {

        MemberResponseDto responseDto = memberService.findById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 회원 정보 수정
     * @param id
     * @param requestDto
     */
    @PatchMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateUserInfo(
            @PathVariable Long id,
            @RequestBody UpdateUserRequestDto requestDto
    ) {
        MemberResponseDto responseDto = memberService.updateUserInfo(id, requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 회원 삭제
     * @param id
     * @param requestDto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(
            @PathVariable Long id,
            @RequestBody DeleteMemberRequestDto requestDto
    ) {
        memberService.softDelete(id, requestDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
