package com.example.ch5_1_diary_essential.service;

import com.example.ch5_1_diary_essential.model.dto.schedule.request.CreateScheduleRequestDto;
import com.example.ch5_1_diary_essential.model.dto.schedule.request.DeleteScheduleRequestDto;
import com.example.ch5_1_diary_essential.model.dto.schedule.response.ScheduleResponseDto;
import com.example.ch5_1_diary_essential.model.dto.schedule.request.UpdateScheduleRequestDto;
import com.example.ch5_1_diary_essential.model.entity.Member;
import com.example.ch5_1_diary_essential.model.entity.Schedule;
import com.example.ch5_1_diary_essential.repository.MemberRepository;
import com.example.ch5_1_diary_essential.repository.ScheduleRepository;
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
