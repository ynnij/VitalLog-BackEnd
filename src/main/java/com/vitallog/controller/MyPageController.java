 package com.vitallog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitallog.domain.Exercise;
import com.vitallog.domain.Userlog;
import com.vitallog.dto.UserlogDto;
import com.vitallog.service.MyPageService;

@RequestMapping("/api/vitallog/mypage")
@RestController
public class MyPageController {
	@Autowired
	private MyPageService mypageService;
	
	// 오늘 날짜의 총 운동 시간 및 칼로리
	@GetMapping("/{userid}")
	public UserlogDto getTodayTotalLogs(@PathVariable String userid, String date) {
		return mypageService.getTodayTotalLogs(userid, date);
	}

	
	// 해당 유저의 모든 로그 데이터를 최신순으로
	@GetMapping("/{userid}/total") 
	public Map<String, List<UserlogDto>> getUserLogs(@PathVariable String userid) {
		return mypageService.getUserLogs(userid);
	}
	
	// 전체 운동 목록 가져오기
	@GetMapping("/exercise") 
	public List<Exercise> getExercise() {
		return mypageService.getExercise();
	}
	
	// 오늘의 로그 기록하기
	@PostMapping("/{userid}/logs") 
	public ResponseEntity<?> recordLog(@PathVariable String userid, @RequestBody Userlog userlog) {
		return mypageService.recordLog(userid, userlog);
	}
	
	// 오늘 날짜 로그 데이터 목록
	@GetMapping("/{userid}/today")
	public List<UserlogDto> getTodayLogs(@PathVariable String userid, String date){
		return mypageService.getTodayLogs(userid, date);
	}
	
	// 특정 기간 내의 로그 데이터
	@GetMapping("/{userid}/periods")
	public Map<String, List<UserlogDto>> getPeriodsLogs(@PathVariable String userid, String from, String to){
		return mypageService.getPeriodsLogs(userid, from, to);
	}
	
	//로그 삭제하기
	@DeleteMapping("/{logid}")
	public ResponseEntity<?> deleteLog(@PathVariable int logid){
		return mypageService.deleteLog(logid);
	}
}
