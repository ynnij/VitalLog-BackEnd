package com.vitallog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitallog.domain.Member;
import com.vitallog.dto.UserlogDto;
import com.vitallog.persistence.LogsViewRepository;
import com.vitallog.persistence.MemberRepository;

@Service
public class UserMainService {
	@Autowired
	private LogsViewRepository logsviewRepository;
	@Autowired
	private MemberRepository memberRepo;
	
	// 메인페이지 최신 로그 3개
	public Map<String, List<UserlogDto>> getMainPageUserLogs(String userid) {
		Map<String, List<UserlogDto>> data = new HashMap<>();
		
		//로그가 없어도 유저의 이름을 반환해야 한다. 
		List<UserlogDto> user = new ArrayList<>();
		Member member = memberRepo.findById(userid).get();
		user.add(UserlogDto.builder().name(member.getName()).build());
				
		List<Object[]> list = logsviewRepository.getMainPageUserLogs(userid);
		List<UserlogDto> dtolist = new ArrayList<>();
		for(Object[] o : list) {
			dtolist.add(UserlogDto.builder()
					.seq((int)o[0])
					.exerDate((Date) o[1])
					.exerTime((int)o[2])
					.kcal((double)o[3])
					.name((String)o[4])
					.exercise((String)o[5])
					.build());
		}
		data.put("user", user);
		data.put("log", dtolist);
		
		return data;
	}
}
