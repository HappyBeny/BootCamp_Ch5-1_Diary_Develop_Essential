package com.example.ch3_2_diary.repository;

import com.example.ch3_2_diary.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUsername(String username);
    Optional<Member> findMemberByEmail(String email);

    default Member findByEmailOrElseThrow(String email) {
        Member foundMember = findMemberByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Member not found"));

        if (foundMember.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member is deleted, email = " + foundMember.getEmail());
        }

        return foundMember;
    }

    default Member findMemberByUsernameOrElseThrow(String username) {
        Member foundMember = findMemberByUsername(username)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Does not exist username = " + username)
                );

            if (foundMember.isDeleted()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member is deleted, name = " + username);
            }

        return foundMember;
    }


    default Member findByIdOrElseThrow(Long id) {
        Member foundMember = findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Does not exist id = " + id)
                );

        if (foundMember.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member is deleted, id = " + id);
        }

        return foundMember;
    }
}
