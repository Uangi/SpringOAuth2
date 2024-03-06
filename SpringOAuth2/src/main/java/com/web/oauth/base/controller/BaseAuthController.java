package com.web.oauth.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.oauth.base.dto.SessionUser;
import com.web.oauth.base.service.LoginUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BaseAuthController {

	//���Ǹ���
	//private final HttpSession httpSession;
	
	@GetMapping("/")
	public String index(Model model,@LoginUser SessionUser user) {
		
		//�÷����� user ���� �� �־�?
		//SessionUser user = (SessionUser)httpSession.getAttribute("user");
		
		//���ǿ� ������
		if(user!=null) {
			
			//�α����� �ߴ�
			model.addAttribute("email", user.getEmail()); 
			model.addAttribute("name", user.getName()); 
			model.addAttribute("picture", user.getPicture()); 
		}
		
		return "index";
	
	}
	
}
