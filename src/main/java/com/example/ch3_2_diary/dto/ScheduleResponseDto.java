package com.example.ch3_2_diary.dto;

import com.example.ch3_2_diary.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;
    private final Long memberId;
    private final String schedule;
    private String description;

    public ScheduleResponseDto(Long id, Long memberId, String schedule, String description) {
        this.id = id;
        this.memberId = memberId;
        this.schedule = schedule;
        this.description = description;
    }

    public static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getMemberId(), schedule.getSchedule(), schedule.getDescription());
    }
}
