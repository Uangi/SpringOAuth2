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
		
		//���ǿ� ���� �ö�Դ��� �ȿö�Դ��� �Ǵ�����
		resolvers.add(loginUserArgmentResolver);
		
	}
	
	
}
