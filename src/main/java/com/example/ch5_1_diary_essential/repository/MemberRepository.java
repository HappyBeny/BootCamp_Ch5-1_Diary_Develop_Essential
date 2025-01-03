package com.example.ch5_1_diary_essential.repository;

import com.example.ch5_1_diary_essential.common.exception.MemberNotFoundException;
import com.example.ch5_1_diary_essential.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUsername(String username);
    Optional<Member> findMemberByEmail(String email);

    default Member findByEmailOrElseThrow(String email) {
        Member foundMember = findMemberByEmail(email)
                .orElseThrow(() ->
                        new MemberNotFoundException(""));

        if (foundMember.isDeleted()) {
            throw new MemberNotFoundException("deleted");
        }

        return foundMember;
    }

    default Member findMemberByUsernameOrElseThrow(String username) {
        Member foundMember = findMemberByUsername(username)
                .orElseThrow(() ->
                        new MemberNotFoundException(""));

            if (foundMember.isDeleted()) {
                throw new MemberNotFoundException("deleted");
            }

        return foundMember;
    }


    default Member findByIdOrElseThrow(Long id) {
        Member foundMember = findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException(""));

        if (foundMember.isDeleted()) {
            throw new MemberNotFoundException("deleted");
        }

        return foundMember;
    }
}
