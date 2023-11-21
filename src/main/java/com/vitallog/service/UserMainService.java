package com.vitallog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitallog.dto.UserlogDto;
import com.vitallog.persistence.LogsViewRepository;

@Service
public class UserMainService {
	@Autowired
	private LogsViewRepository logsviewRepository;
	
	// 메인페이지 최신 로그 3개
	public List<UserlogDto> getMainPageUserLogs(String userid) {
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
		
		return dtolist;
	}
}
