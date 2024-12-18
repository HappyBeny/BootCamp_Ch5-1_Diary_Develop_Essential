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

    @ManyToOne
    @JoinColumn(name = "member_Id")
    private Member member;

    @Column(nullable = false)
    private String schedule;

    @Column
    private String description;

    public Schedule() {
    }

    public Schedule(Member member, String schedule, String description) {
        this.member = member;
        this.schedule = schedule;
        this.description = description;
    }
}
