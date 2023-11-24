package com.vitallog.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.vitallog.domain.Exercise;
import com.vitallog.domain.Userlog;
import com.vitallog.dto.UserlogDto;
import com.vitallog.persistence.ExerciseRepository;
import com.vitallog.persistence.LogsViewRepository;
import com.vitallog.persistence.MemberRepository;
import com.vitallog.persistence.UserlogRepository;

import jakarta.transaction.Transactional;

@Service
public class MyPageService {
	@Autowired
	private UserlogRepository logRepository;
	@Autowired
	private ExerciseRepository exerciseRepository;
	@Autowired
	private LogsViewRepository logsviewRepository;
	@Autowired
	private MemberRepository memberRepository;
	
	private List<UserlogDto> objectToDto(List<Object[]> list){
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
	
	private List<UserlogDto> objectToTotalLogDto(List<Object[]> list){
		List<UserlogDto> dtolist = new ArrayList<>();
		for (Object[] o : list) {
			dtolist.add(UserlogDto.builder()
					.exerDate((Date) o[0])
					.totalExerTime((BigDecimal) o[1])
					.totalKcal((double) o[2])
					.build());
			
		}
		return dtolist;
	}
	
	// 메인페이지 최신 로그 3개
	public List<UserlogDto> getMainPageUserLogs(String userid) {
		List<Object[]> list = logsviewRepository.getMainPageUserLogs(userid);
		
		return objectToDto(list);
	}
	
	// 오늘의 종합 운동량, 칼로리
	public UserlogDto getTodayTotalLogs(String userid, String date) {
		List<Object[]> list = logsviewRepository.getTodayTotalLogs(userid, date);
		String username = memberRepository.findById(userid).get().getName();
		UserlogDto dto = new UserlogDto();
		for(Object[]o: list) {
			dto.setName(username);
			dto.setTotalKcal((double)(o[1]==null ? 0.0 : o[1]));
			dto.setTotalExerTime((BigDecimal)o[2]);
			dto.setExerDate((Date)o[3]);
		}
		
		return dto;
	}
	
	// 유저의 모든 로그 데이터(dailytotal도 같이 담아서 Map return)
	public Map<String, List<UserlogDto>> getUserLogs(String userid) {
		Map<String, List<UserlogDto>> data = new HashMap<>();

		List<Object[]> list = logsviewRepository.getUserLogs(userid);
		List<Object[]> totalList = logsviewRepository.getUserLogsTotal(userid);
		
		data.put("userlog",  objectToDto(list));
		data.put("dailyTotalLog", objectToTotalLogDto(totalList));

		return data;

	}
	// 전체 운동 목록
	public List<Exercise> getExercise(){ 
		return exerciseRepository.findAll();
	}
	
	 //오늘의 운동 추가
    @Transactional
	public ResponseEntity<?> recordLog(String userid, Userlog userlog){
		userlog.setMemberid(userid);
		logRepository.save(userlog);
		return ResponseEntity.ok(null);
	}
	
	// 오늘 날짜의 로그 데이터
	public List<UserlogDto> getTodayLogs(String userid, String date){
		List <Object[]> list = logsviewRepository.getTodayLogs(userid, date);
		
		return objectToDto(list);
	}

	
	// 특정 기간 내의 로그 데이터
	public Map<String, List<UserlogDto>> getPeriodsLogs(String userid, String fromDate, String toDate){
		Map<String, List<UserlogDto>> data = new HashMap<>();

		List <Object[]> list = logsviewRepository.getPeriodsLogs(userid, fromDate, toDate);		
		List<Object[]> totalList = logsviewRepository.getPeriodsLogsTotal(userid,fromDate,toDate);

		data.put("userlog", objectToDto(list));
		data.put("dailyTotalLog", objectToTotalLogDto(totalList));
		
		return data;
	}

	
	//로그 삭제하기
    @Transactional
	public ResponseEntity<?> deleteLog(int logid){
		logRepository.deleteById(logid);
		return ResponseEntity.ok(null);
	}
}
