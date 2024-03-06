package com.web.oauth.base.dto;

import java.util.Map;

import com.web.oauth.base.model.BaseAuthRole;
import com.web.oauth.base.model.BaseAuthUser;

import lombok.Builder;
import lombok.Getter;

//���ۿ��� �Ѿ���� ������ [�̸�/�̹���/�����ʻ���] ��Ƶδ� �� 

@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;
	
	//�Ѿ���� �����͸� (Map<String, Object> attributes,#######)�� ��������
	@Builder
	public OAuthAttributes(Map<String, Object> attributes,String nameAttributeKey, String name, String email, String picture) {
		
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
		
	}
	
	//����,īī��,���̹� ���� ���� (ofGoogle, ofNaver, ofKaKao)
	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		
		if(registrationId.equals("kakao")) {
			return ofKakao(userNameAttributeName,attributes);//id�� �߰����ָ� ��
		}
		
		if(registrationId.equals("naver")) {
			return ofNaver("id",attributes);// response
		}
		
		
		
		//userNameAttributeName : ������ sub��� ���� �Ѿ��
		//Google�� ������
		//MAP ������ attributes�� �Ѿ���⶧���� �Ѹ� �� 
		return ofGoogle(userNameAttributeName,attributes);
	}
	
	
	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		
		//Map�ٿ�ĳ����
		//kakao_account �޾Ƴ�
		Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
		
		//�� kakaoAccount�ȿ� �ִ� profile �޾Ƴ�
		Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
		
		return OAuthAttributes.builder()
				.name((String)kakaoProfile.get("nickname"))
				.email("1")
				.picture((String)kakaoProfile.get("profile_image_url"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}
	
	private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		
		return OAuthAttributes.builder()
				.name((String)attributes.get("name"))
				.email((String)attributes.get("email"))
				.picture((String)attributes.get("picture"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}
	
	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		
		
		Map<String, Object> response = (Map<String, Object>)attributes.get("response");
		
		return OAuthAttributes.builder()
				.name((String)response.get("name"))
				.email((String)response.get("email"))
				.picture((String)response.get("profile_image"))
				.attributes(response)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}
	
	public BaseAuthUser toEntity() {
		
		return BaseAuthUser.builder()
				.name(name)
				.email(email)
				.picture(picture)
				.role(BaseAuthRole.GUEST)
				.build();
		
	}
	
	
}
