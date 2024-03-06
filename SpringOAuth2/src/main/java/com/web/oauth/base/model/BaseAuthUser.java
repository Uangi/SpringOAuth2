package com.web.oauth.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@NoArgsConstructor : 기본생성자로 객체생성 할 수 없으니 , 오버로딩 된 생성자로만 할 수 있음!
@Getter
@NoArgsConstructor
@Entity
public class BaseAuthUser {
	
	//로그인정보를 DB에 저장해야하니까 @Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	//계정 - 프로필 사진
	@Column
	private String picture;
	
	
	//	admin & user 만들었던 그거
	//	(EnumType.STRING) : 기본값은 string으로 저장하겠음
	//	@Column(nullable : false) =  false면 안돼
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BaseAuthRole role;
	
	
	//오버로딩 된 생성자
	//@Builder : 객체가 만들어짐
	@Builder
	public BaseAuthUser(String name,String email,String picture,BaseAuthRole role) {
		
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.role = role;
		
	}
	
	//회원정보 수정
	//구글에서 바꾸면 내 사이트에서도 바로 반영되게끔 하는 코딩
	
	public BaseAuthUser update(String name,String picture) {
		
		this.name = name;
		this.picture = picture;
		
		return this;
	}
	
	public String getRoleKey() {
		return this.role.getKey();
		
	}
	
	
	
	
	
	
	
	
	
}
