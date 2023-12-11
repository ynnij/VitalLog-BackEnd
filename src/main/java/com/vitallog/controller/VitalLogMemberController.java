package com.vitallog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vitallog.domain.Member;
import com.vitallog.service.VitalLogMemberService;

@RestController
public class VitalLogMemberController {
	@Autowired
	private VitalLogMemberService memberService;
	
	@PostMapping("/login")
	public void login() {}
	
	@PostMapping("/api/vitallog/register")
	public ResponseEntity<?> memberRegister(@RequestBody Member member){
		return memberService.memberRegister(member);
	}
	
	@GetMapping("/api/vitallog/check-id")
	public ResponseEntity<?> checkId(String id){
		return memberService.checkId(id);
	}
}
