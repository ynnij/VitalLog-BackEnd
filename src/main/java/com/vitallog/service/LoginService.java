package com.vitallog.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vitallog.domain.Member;

@Service
public class LoginService {
	@Autowired
	private AuthenticationConfiguration authConfig;
	
	public String loginProc(Member member) throws Exception {
		
		//security에게 로그인 요청에 필요한 객체 생성
		Authentication authToken = new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword());
		
		
		//로그인을 실행하는 Security 객체 생성
		AuthenticationManager authManager = authConfig.getAuthenticationManager();
		
		//인증진행
		Authentication auth = authManager.authenticate(authToken);
		System.out.println("auth:"+auth);
		
		String token =JWT.create()
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*30)) //유효시간 30분
				.withClaim("id", member.getId()) //토큰에 등록할 데이터
				.withClaim("name",member.getName())
				.sign(Algorithm.HMAC256("com.vitallog.jwt")); //토큰암호화
		
		return "login";
	}
}
