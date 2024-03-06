package com.web.oauth.base.service;

import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.web.oauth.base.dto.SessionUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LoginUserArgmentResolver implements HandlerMethodArgumentResolver {

	private final HttpSession httpSession;

	
	//세션에 값이 있니?없니?
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		//로그인 정보 값이 있는거
		boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class)!=null;
		
		//파라미터 값이 있는거
		boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
		
		//위 두개가 && 있을 때
		return isLoginUserAnnotation && isUserClass;
		
	}

	
	//실제론 이 코딩이 세션에 값을 올려줌
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		return httpSession.getAttribute("user");
	}
	
}
