package com.vitallog.config.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitallog.domain.Member;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper mapper = new ObjectMapper();
		Member member= null;
		
		try {
			member = mapper.readValue(request.getInputStream(), Member.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Authentication authToken = new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword());
		
		Authentication auth = authenticationManager.authenticate(authToken);
		System.out.println("auth: "+auth);
		
		return auth;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User)authResult.getPrincipal();
		
		String token = JWT.create()
				.withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60))
				.withClaim("userid", user.getUsername())
				.sign(Algorithm.HMAC256("com.vitallog.jwt"));
		System.out.println("token:"+token);
		response.addHeader("Authorization","Bearer "+token);
		
	}
}
