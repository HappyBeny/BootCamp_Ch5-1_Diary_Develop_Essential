package com.example.ch3_2_diary.service;

import com.example.ch3_2_diary.dto.CreateScheduleRequestDto;
import com.example.ch3_2_diary.dto.ScheduleResponseDto;
import com.example.ch3_2_diary.entity.Member;
import com.example.ch3_2_diary.entity.Schedule;
import com.example.ch3_2_diary.repository.MemberRepository;
import com.example.ch3_2_diary.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    public ScheduleResponseDto create(CreateScheduleRequestDto requestDto) {

        log.info(":::finding Member with id : {}", requestDto.getMemberId());

        memberRepository.findByIdOrElseThrow(requestDto.getMemberId());

        Schedule savedSchedule = scheduleRepository.save(
                new Schedule(
                        requestDto.getMemberId(),
                        requestDto.getSchedule(),
                        requestDto.getDescription() != null ? requestDto.getDescription() : "")
        );

        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getMemberId(),
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
        log.info("finding schedule with id : {}", id);

        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(
                foundSchedule.getId(),
                foundSchedule.getMemberId(),
                foundSchedule.getSchedule(),
                foundSchedule.getDescription());
    }

    public ScheduleResponseDto update(Long id, CreateScheduleRequestDto requestDto){
        log.info("finding schedule with id : {}", id);
        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        log.info("find member with id : {}", requestDto.getMemberId());
        Member member = memberRepository.findByIdOrElseThrow(requestDto.getMemberId());

        foundSchedule.setMemberId(requestDto.getMemberId());
        foundSchedule.setSchedule(requestDto.getSchedule());
        foundSchedule.setDescription(requestDto.getDescription());

        Schedule updatedSchedule = scheduleRepository.save(foundSchedule);

        return new ScheduleResponseDto(
                updatedSchedule.getId(),
                updatedSchedule.getMemberId(),
                updatedSchedule.getSchedule(),
                updatedSchedule.getDescription()
        );
    }

    public void delete(Long id) {
        log.info("finding schedule with id : {}", id);

        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(foundSchedule);
    }
}
