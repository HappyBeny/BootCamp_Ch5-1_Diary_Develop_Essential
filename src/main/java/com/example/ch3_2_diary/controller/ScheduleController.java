package com.example.ch3_2_diary.controller;

import com.example.ch3_2_diary.dto.CreateScheduleRequestDto;
import com.example.ch3_2_diary.dto.DeleteScheduleRequestDto;
import com.example.ch3_2_diary.dto.ScheduleResponseDto;
import com.example.ch3_2_diary.dto.UpdateScheduleRequestDto;
import com.example.ch3_2_diary.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 게시물 생성
     * @param requestDto
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(
            @RequestBody CreateScheduleRequestDto requestDto,
            HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");

        ScheduleResponseDto responseDto = scheduleService.create(requestDto, username);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 일정 전체 조회
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }

    /**
     * 일정 단건 조회
     * @param id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {

        ScheduleResponseDto responseDto = scheduleService.findById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 일정 수정
     * @param id
     * @param requestDto
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id, @RequestBody UpdateScheduleRequestDto requestDto,
            HttpSession session
    ) {
        ScheduleResponseDto responseDto = scheduleService.update(session,id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 일정 삭제
     * @param id
     * @param requestDto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id, @RequestBody DeleteScheduleRequestDto requestDto,
            HttpSession session) {
        scheduleService.delete(session,id, requestDto);
        return new ResponseEntity<>("Delete Complete",HttpStatus.OK);
    }
}
