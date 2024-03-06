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

//@NoArgsConstructor : �⺻�����ڷ� ��ü���� �� �� ������ , �����ε� �� �����ڷθ� �� �� ����!
@Getter
@NoArgsConstructor
@Entity
public class BaseAuthUser {
	
	//�α��������� DB�� �����ؾ��ϴϱ� @Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	//���� - ������ ����
	@Column
	private String picture;
	
	
	//	admin & user ������� �װ�
	//	(EnumType.STRING) : �⺻���� string���� �����ϰ���
	//	@Column(nullable : false) =  false�� �ȵ�
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BaseAuthRole role;
	
	
	//�����ε� �� ������
	//@Builder : ��ü�� �������
	@Builder
	public BaseAuthUser(String name,String email,String picture,BaseAuthRole role) {
		
		this.name = name;
		this.email = email;
		this.picture = picture;
		this.role = role;
		
	}
	
	//ȸ������ ����
	//���ۿ��� �ٲٸ� �� ����Ʈ������ �ٷ� �ݿ��ǰԲ� �ϴ� �ڵ�
	
	public BaseAuthUser update(String name,String picture) {
		
		this.name = name;
		this.picture = picture;
		
		return this;
	}
	
	public String getRoleKey() {
		return this.role.getKey();
		
	}
	
	
	
	
	
	
	
	
	
}
