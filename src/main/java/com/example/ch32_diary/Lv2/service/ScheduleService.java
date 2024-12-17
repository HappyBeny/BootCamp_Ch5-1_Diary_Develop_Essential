package com.example.ch32_diary.Lv2.service;

import com.example.ch32_diary.Lv2.dto.CreateScheduleRequestDto;
import com.example.ch32_diary.Lv2.dto.ScheduleResponseDto;
import com.example.ch32_diary.Lv2.entity.Schedule;
import com.example.ch32_diary.Lv2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto create(CreateScheduleRequestDto requestDto) {

        Schedule savedSchedule = scheduleRepository.save(
                new Schedule(
                        requestDto.getWriter(),
                        requestDto.getSchedule(),
                        requestDto.getDescription() != null ? requestDto.getDescription() : "")
        );

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
        log.info("finding schedule with id : {}", id);

        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(
                foundSchedule.getId(),
                foundSchedule.getWriter(),
                foundSchedule.getSchedule(),
                foundSchedule.getDescription());
    }

    public ScheduleResponseDto update(Long id, CreateScheduleRequestDto requestDto){
        log.info("finding schedule with id : {}", id);

        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);

        foundSchedule.setWriter(requestDto.getWriter());
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

    public void delete(Long id) {
        log.info("finding schedule with id : {}", id);

        Schedule foundSchedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(foundSchedule);
    }
}
