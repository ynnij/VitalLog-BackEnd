package com.vitallog.config.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vitallog.domain.Member;
import com.vitallog.persistence.MemberRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private final MemberRepository memberRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String srcToken = request.getHeader("Authorization");
		if(srcToken == null || !srcToken.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String jwtToken = srcToken.replace("Bearer ","");
		
		String userid = JWT.require(Algorithm.HMAC256("com.vitallog.jwt"))
				.build().verify(jwtToken).getClaim("userid").asString();
		System.out.println("userid:"+userid);
		
		Optional<Member> opt = memberRepository.findById(userid);
		if(!opt.isPresent()) {
			filterChain.doFilter(request, response);
			return;
		}
		
		Member findMember = opt.get();
		
		User user = new User(findMember.getId(), findMember.getPassword(), AuthorityUtils.createAuthorityList());
		
		Authentication auth = new UsernamePasswordAuthenticationToken(user,  null, user.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
	}
}
