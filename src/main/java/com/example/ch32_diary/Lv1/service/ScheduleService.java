package com.example.ch32_diary.Lv1.service;

import com.example.ch32_diary.Lv1.dto.CreateScheduleRequestDto;
import com.example.ch32_diary.Lv1.dto.ScheduleResponseDto;
import com.example.ch32_diary.Lv1.entity.Schedule;
import com.example.ch32_diary.Lv1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto create(CreateScheduleRequestDto requestDto) {
        Schedule newSchedule = new Schedule(
                requestDto.getWriter(),
                requestDto.getSchedule(),
                requestDto.getDescription() != null ? requestDto.getDescription() : ""
        );
        Schedule savedSchedule = scheduleRepository.save(newSchedule);

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
}
