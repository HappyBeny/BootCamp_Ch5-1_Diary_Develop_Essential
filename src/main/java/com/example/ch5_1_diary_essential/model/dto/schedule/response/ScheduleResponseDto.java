package com.example.ch5_1_diary_essential.model.dto.schedule.response;

import com.example.ch5_1_diary_essential.model.entity.Schedule;
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
