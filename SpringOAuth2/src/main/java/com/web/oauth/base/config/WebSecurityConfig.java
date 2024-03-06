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

//@EnableWebSecurity : 스프링의 security를 활성화 시킴
@Controller
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	//의존성주입시킴
	@Autowired
	private final BaseCustomOAuth2UserService baseCustomOAuth2UserService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		//csrf(=내가 만든 html에만 사용/나타내는) 사용하지마 (=google에서 만들어서 보내주는데 google은 csrf가 없어서)
		//.antMatchers("/api/v1/**") (=google에서 보내주는 주소인데 우리눈에는 안보임 - 정해져있다는 뜻)
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
