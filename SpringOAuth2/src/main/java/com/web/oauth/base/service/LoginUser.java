package com.web.oauth.base.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@LoginUser ������̼��� �����ϱ����� �ڵ�

//@interface : ������̼� Ŭ������ �����ϰڴ�
//������̼��� ������ �� �ִ� ��ġ ����
//parameter : �޼ҵ忡�ٰ� �ִ� ������̼���(����� ��ü���� ��밡��)
//������̼� ������ �󸶳� �������� �����Ұ���?
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME) 
public @interface LoginUser {

	
}


//SessionUser user = (SessionUser)httpSession.getAttribute("user");