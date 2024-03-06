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

	
	//���ǿ� ���� �ִ�?����?
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		//�α��� ���� ���� �ִ°�
		boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class)!=null;
		
		//�Ķ���� ���� �ִ°�
		boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
		
		//�� �ΰ��� && ���� ��
		return isLoginUserAnnotation && isUserClass;
		
	}

	
	//������ �� �ڵ��� ���ǿ� ���� �÷���
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		return httpSession.getAttribute("user");
	}
	
}
