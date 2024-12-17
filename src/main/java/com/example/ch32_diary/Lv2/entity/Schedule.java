package com.example.ch32_diary.Lv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String schedule;

    @Column
    private String description;

    public Schedule() {
    }

    public Schedule(String writer, String schedule, String description) {
        this.writer = writer;
        this.schedule = schedule;
        this.description = description;
    }
}
