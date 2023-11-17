package com.vitallog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vitallog.domain.Member;
import com.vitallog.persistence.MemberRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Override  //id로 받기 위해
	public UserDetails loadUserByUsername(@Param(value="id") String username) throws UsernameNotFoundException {
		System.out.println("username: "+username);
		Member member = memberRepository.findById(username).orElseThrow(()->
			new UsernameNotFoundException("Not Found"));
		
		return new User(member.getId(), member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}

}
