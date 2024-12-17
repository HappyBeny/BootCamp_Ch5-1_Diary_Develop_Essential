package com.example.ch3_2_diary.entity;

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
    private Long memberId;

    @Column(nullable = false)
    private String schedule;

    @Column
    private String description;

    public Schedule() {
    }

    public Schedule(Long memberId, String schedule, String description) {
        this.memberId = memberId;
        this.schedule = schedule;
        this.description = description;
    }
}
