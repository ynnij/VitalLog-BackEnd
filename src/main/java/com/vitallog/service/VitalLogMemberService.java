package com.vitallog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.vitallog.domain.Member;
import com.vitallog.domain.Role;
import com.vitallog.persistence.MemberRepository;

import jakarta.transaction.Transactional;

@Service
public class VitalLogMemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
    @Transactional
	public ResponseEntity<?> memberRegister(@RequestBody Member member) {
		if(memberRepository.findById(member.getId()).isPresent()) // 데이터베이스에 이미 존재하는 아이디인 경우
			return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다.");
		
		member.setRole(Role.ROLE_MEMBER); //member Role 
		member.setPassword(encoder.encode(member.getPassword())); //패스워드 인코딩
		memberRepository.save(member);
		
		
		return ResponseEntity.ok("회원가입 완료");
	}
}
