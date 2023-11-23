package com.vitallog;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.querydsl.core.BooleanBuilder;
import com.vitallog.domain.Information;
import com.vitallog.domain.LogsView;
import com.vitallog.domain.Member;
import com.vitallog.domain.QInformation;
import com.vitallog.domain.Role;
import com.vitallog.domain.Userlog;
import com.vitallog.dto.UserlogDto;
import com.vitallog.persistence.MemberRepository;
import com.vitallog.persistence.UserlogRepository;
import com.vitallog.persistence.InformationRepository;
import com.vitallog.persistence.LogsViewRepository;

@SpringBootTest
public class VitalLogDevTest {
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private UserlogRepository logRepo;
	
	@Autowired
	private LogsViewRepository viewRepo;
	
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Autowired
	private InformationRepository infoRepo;
	
	@Test
	public void userlogTest() {
		try {
			
			List<Object[]> list = viewRepo.getTodayTotalLogs("user1", "2023-11-20" );
			UserlogDto dto = new UserlogDto();
			for(Object[]o: list) {
				dto.setName((String)o[0]);
				dto.setTotalKcal((double)o[1]);
				dto.setTotalExerTime((BigDecimal)o[2]);
				dto.setExerDate((Date)o[3]);
			}
			System.out.println(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void viewTest() {
		List<LogsView> list = viewRepo.findAll();
		for(LogsView l : list) {
			System.out.println(l);
		}
	}
	
	//@Test
	public void logCreateTest() {
		logRepo.save(Userlog.builder()
				.exerDate(new Date())
				.kcal(1.2)
				.exerTime(10)
				.exerid("e113")
				.memberid("user1")
				.build());
	}
	
	//@Test
	public void initMemberTest() {
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
	
	//@Test
	public void getInformationTest() {
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
