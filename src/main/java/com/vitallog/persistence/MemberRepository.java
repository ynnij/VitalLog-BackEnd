package com.vitallog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitallog.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
