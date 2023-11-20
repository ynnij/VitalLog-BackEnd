package com.vitallog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.vitallog.config.filter.JWTAuthenticationFilter;
import com.vitallog.config.filter.JWTAuthorizationFilter;
import com.vitallog.persistence.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecuirtyConfig {
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	private MemberRepository memberRepository;

	@Bean
	PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());

		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(new AntPathRequestMatcher("/user/**")).authenticated() 
				.requestMatchers(new AntPathRequestMatcher("/community/**")).authenticated() //커뮤니티 회원만 접근가능
				.requestMatchers(new AntPathRequestMatcher("/mypage/**")).authenticated() //mypage는 회원만 접근가능
				.anyRequest().permitAll()); 

		http.formLogin(frmLogin -> frmLogin.disable());
		http.httpBasic(basic -> basic.disable());

		http.sessionManagement(ssmn->ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	      
		// 스프링 시큐리티가 등록한 필터체인의 뒤에 작성한 필터 추가
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));

		// 스프링 시큐리티가 등록한 필터들 중 AuthorizationFilter 앞에 앞에서 작성한 필터 삽입
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepository), AuthorizationFilter.class);

		return http.build();
	}

	
}
