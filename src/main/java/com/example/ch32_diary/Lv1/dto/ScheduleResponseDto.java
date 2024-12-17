package com.example.ch32_diary.Lv1.dto;

import com.example.ch32_diary.Lv1.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final String writer;
    private final String schedule;
    private String description;

    public ScheduleResponseDto(Long id, String writer, String schedule, String description) {
        this.id = id;
        this.writer = writer;
        this.schedule = schedule;
        this.description = description;
    }

    public static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getWriter(), schedule.getSchedule(), schedule.getDescription());
    }
}
