package com.vitallog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vitallog.dto.UserlogDto;
import com.vitallog.service.UserMainService;

@RestController
public class UserMainController {
	@Autowired
	private UserMainService usermainService; 
	
	// 유저 메인페이지에서 보여주는 최근 3개 로그 가져오기
	@GetMapping("/api/vitallog/user/{userid}")
	public List<UserlogDto> getMainPageUserLogs(@PathVariable String userid) {
		return usermainService.getMainPageUserLogs(userid);
	}
}
