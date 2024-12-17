package com.example.ch32_diary.Lv1.repository;

import com.example.ch32_diary.Lv1.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
