package com.example.ch3_2_diary.service;

import com.example.ch3_2_diary.dto.CreateScheduleRequestDto;
import com.example.ch3_2_diary.dto.DeleteScheduleRequestDto;
import com.example.ch3_2_diary.dto.ScheduleResponseDto;
import com.example.ch3_2_diary.dto.UpdateScheduleRequestDto;
import com.example.ch3_2_diary.entity.Member;
import com.example.ch3_2_diary.entity.Schedule;
import com.example.ch3_2_diary.repository.MemberRepository;
import com.example.ch3_2_diary.repository.ScheduleRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public ScheduleResponseDto create(CreateScheduleRequestDto requestDto, String username) {

        log.info("Attempting to find Member with username: {}", username);
        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(
                username,
                requestDto.getSchedule(),
                requestDto.getDescription() != null ? requestDto.getDescription() : "");

        schedule.setMember(findMember);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getWriter(),
                savedSchedule.getSchedule(),
                savedSchedule.getDescription()
        );
    }

    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        log.info("finding schedule with scheduleId : {}", id);

        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(
                foundSchedule.getId(),
                foundSchedule.getWriter(),
                foundSchedule.getSchedule(),
                foundSchedule.getDescription());
    }

    public ScheduleResponseDto update(
            HttpSession session,
            Long id, UpdateScheduleRequestDto requestDto){

        log.info("finding schedule with scheduleId : {}", id);
        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        validateOwnerAndPassword(session, foundSchedule, requestDto.getPassword());

        foundSchedule.setSchedule(requestDto.getSchedule());
        foundSchedule.setDescription(requestDto.getDescription());

        Schedule updatedSchedule = scheduleRepository.save(foundSchedule);

        return new ScheduleResponseDto(
                updatedSchedule.getId(),
                updatedSchedule.getWriter(),
                updatedSchedule.getSchedule(),
                updatedSchedule.getDescription()
        );
    }

    public void delete(HttpSession session,
                       Long id, DeleteScheduleRequestDto requestDto) {

        log.info("finding schedule with scheduleId : {}", id);
        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        log.info("checking password : {}", requestDto.getPassword());
        validateOwnerAndPassword(session, foundSchedule, requestDto.getPassword());

        scheduleRepository.delete(foundSchedule);
    }

    public void validateOwnerAndPassword(HttpSession session,Schedule schedule, String password){
        String loggedPassword = (String) session.getAttribute("password");
        String loggedUsername = (String) session.getAttribute("username");

        if (!schedule.getWriter().equals(loggedUsername)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not the owner of this schedule");
        }

        if (!loggedPassword.equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect Password");
        }
    }
}
