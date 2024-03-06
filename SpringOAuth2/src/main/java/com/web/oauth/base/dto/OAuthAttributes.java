package com.web.oauth.base.dto;

import java.util.Map;

import com.web.oauth.base.model.BaseAuthRole;
import com.web.oauth.base.model.BaseAuthUser;

import lombok.Builder;
import lombok.Getter;

//구글에서 넘어오는 데이터 [이름/이미지/프로필사진] 담아두는 곳 

@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;
	
	//넘어오는 데이터를 (Map<String, Object> attributes,#######)에 받을거임
	@Builder
	public OAuthAttributes(Map<String, Object> attributes,String nameAttributeKey, String name, String email, String picture) {
		
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
		
	}
	
	//구글,카카오,네이버 등을 구분 (ofGoogle, ofNaver, ofKaKao)
	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		
		if(registrationId.equals("kakao")) {
			return ofKakao(userNameAttributeName,attributes);//id만 추가해주면 됨
		}
		
		if(registrationId.equals("naver")) {
			return ofNaver("id",attributes);// response
		}
		
		
		
		//userNameAttributeName : 구글은 sub라는 값이 넘어옴
		//Google이 뼈대임
		//MAP 형태의 attributes가 넘어오기때문에 뿌릴 때 
		return ofGoogle(userNameAttributeName,attributes);
	}
	
	
	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		
		//Map다운캐스팅
		//kakao_account 받아냄
		Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
		
		//위 kakaoAccount안에 있는 profile 받아냄
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
