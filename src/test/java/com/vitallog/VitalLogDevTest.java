package com.vitallog;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.querydsl.core.BooleanBuilder;
import com.vitallog.domain.Information;
import com.vitallog.domain.Member;
import com.vitallog.domain.QInformation;
import com.vitallog.domain.Role;
import com.vitallog.persistence.MemberRepository;

import com.vitallog.persistence.InformationRepository;

@SpringBootTest
public class VitalLogDevTest {
	@Autowired
	private MemberRepository memberRepo;
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Autowired
	private InformationRepository infoRepo;
	
	//@Test
	public void initMember() {
		memberRepo.save(Member.builder()
				.id("user1")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_MEMBER)
				.name("user1").build());
		
		memberRepo.save(Member.builder()
				.id("user2")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_MEMBER)
				.name("user2").build());

		memberRepo.save(Member.builder()
				.id("user3")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_MEMBER)
				.name("user3").build());
		
		memberRepo.save(Member.builder()
				.id("admin")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_ADMIN)
				.name("admin").build());
	}
	
	@Test
	public void getTest() {
		String age="30대";
		String bmi="비만전단계";
		String gender="";
		QInformation qinfo = QInformation.information;
		BooleanBuilder builder = new BooleanBuilder();
		
		if(!age.equals("")) {
			builder.and(qinfo.age.eq(age));
		}
		if(!bmi.equals("")) {
			builder.and(qinfo.bmi.eq(bmi));
		}
		if(!gender.equals("")) {
			builder.and(qinfo.gender.eq(gender));
		}
		
		Iterable<Information> result = infoRepo.findAll(builder);
		
		System.out.println(result);
		
	}
	
}
