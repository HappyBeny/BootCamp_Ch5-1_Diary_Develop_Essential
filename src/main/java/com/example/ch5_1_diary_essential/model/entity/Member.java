package com.example.ch5_1_diary_essential.model.entity;

import com.example.ch5_1_diary_essential.model.dto.member.request.UpdateUserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean deleted = false;

    public Member() {
    }

    public Member(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void updateInfo(UpdateUserRequestDto requestDto) {
        this.username = requestDto.getUsername();

        if (requestDto.getNewPassword() != null) {
            this.password = requestDto.getNewPassword();
        }
    }

    /**
     * 비밀번호 검증 메서드
     */
    public void validatePassword(String password){
        if (!this.password.equals(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong password");
        }
    }

    /**
     * 소프트 딜리트
     */
    public void softDelete(){
        this.deleted = true;
    }
}
