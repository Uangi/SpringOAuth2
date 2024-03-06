package com.web.oauth.base.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.web.oauth.base.service.LoginUserArgmentResolver;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final LoginUserArgmentResolver loginUserArgmentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		
		//세션에 값이 올라왔는지 안올라왔는지 판단해줌
		resolvers.add(loginUserArgmentResolver);
		
	}
	
	
}
