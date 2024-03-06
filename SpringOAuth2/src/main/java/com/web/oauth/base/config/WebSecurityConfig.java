package com.web.oauth.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

import com.web.oauth.base.model.BaseAuthRole;
import com.web.oauth.base.service.BaseCustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

//@EnableWebSecurity : �������� security�� Ȱ��ȭ ��Ŵ
@Controller
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	//���������Խ�Ŵ
	@Autowired
	private final BaseCustomOAuth2UserService baseCustomOAuth2UserService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		//csrf(=���� ���� html���� ���/��Ÿ����) ��������� (=google���� ���� �����ִµ� google�� csrf�� ���)
		//.antMatchers("/api/v1/**") (=google���� �����ִ� �ּ��ε� �츮������ �Ⱥ��� - �������ִٴ� ��)
		http
		.csrf().disable().headers().frameOptions().disable()
		.and()
		.authorizeRequests()
		.antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
		.antMatchers("/api/v1/**").hasRole(BaseAuthRole.USER.name())
		.anyRequest().authenticated()
		.and()
		.logout().logoutSuccessUrl("/")
		.deleteCookies("JSESSIONID").invalidateHttpSession(true) 
		.and()
		.oauth2Login().defaultSuccessUrl("/").userInfoEndpoint()
		.userService(baseCustomOAuth2UserService)
		;
		
		return http.build();
	}
	
}
