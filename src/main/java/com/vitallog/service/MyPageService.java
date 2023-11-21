package com.vitallog.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.vitallog.domain.Exercise;
import com.vitallog.domain.Userlog;
import com.vitallog.dto.UserlogDto;
import com.vitallog.persistence.ExerciseRepository;
import com.vitallog.persistence.LogsViewRepository;
import com.vitallog.persistence.UserlogRepository;

@Service
public class MyPageService {
	@Autowired
	private UserlogRepository logRepository;
	@Autowired
	private ExerciseRepository exerciseRepository;
	
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
	
	// 오늘의 종합 운동량, 칼로리
	public UserlogDto getTodayTotalLogs(String userid, String date) {
		List<Object[]> list = logsviewRepository.getTodayTotalLogs(userid, date);
		UserlogDto dto = new UserlogDto();
		for(Object[]o: list) {
			dto.setName((String)o[0]);
			dto.setTotalKcal((double)o[1]);
			dto.setTotalExerTime((BigDecimal)o[2]);
			dto.setExerDate((Date)o[3]);
		}
		
		return dto;
	}
	
	// 유저의 모든 로그 데이터
	public List<UserlogDto> getUserLogs (String userid) { 
		List<Object[]> list = logsviewRepository.getUserLogs(userid);
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
	
	// 전체 운동 목록
	public List<Exercise> getExercise(){ 
		return exerciseRepository.findAll();
	}
	
	 //오늘의 운동 추가
	public ResponseEntity<?> recordLog(String userid, Userlog userlog){
		userlog.setMemberid(userid);
		logRepository.save(userlog);
		return ResponseEntity.ok(null);
	}
	
	// 오늘 날짜의 로그 데이터
	public List<UserlogDto> getTodayLogs(String userid, String date){
		List <Object[]> list = logsviewRepository.getTodayLogs(userid, date);
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

	
	// 특정 기간 내의 로그 데이터
	public List<UserlogDto> getPeriodsLogs(String userid, String fromDate, String toDate){
		List <Object[]> list = logsviewRepository.getPeriodsLogs(userid, fromDate, toDate);
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

	
	//로그 삭제하기
	public ResponseEntity<?> deleteLog(int logid){
		logRepository.deleteById(logid);
		return ResponseEntity.ok(null);
	}
}
