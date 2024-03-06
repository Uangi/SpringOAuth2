package com.web.oauth.base.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@LoginUser 어노테이션을 생성하기위한 코딩

//@interface : 어노테이션 클래스로 지정하겠다
//어노테이션이 생성될 수 있는 위치 지정
//parameter : 메소드에다가 주는 어노테이션임(선언된 객체에서 사용가능)
//어노테이션 정보를 얼마나 오랫동안 유지할건지?
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME) 
public @interface LoginUser {

	
}


//SessionUser user = (SessionUser)httpSession.getAttribute("user");