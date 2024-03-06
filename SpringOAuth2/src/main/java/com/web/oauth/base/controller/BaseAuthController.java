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

	//세션만듦
	//private final HttpSession httpSession;
	
	@GetMapping("/")
	public String index(Model model,@LoginUser SessionUser user) {
		
		//올려놓은 user 세션 값 있어?
		//SessionUser user = (SessionUser)httpSession.getAttribute("user");
		
		//세션에 있으면
		if(user!=null) {
			
			//로그인을 했다
			model.addAttribute("email", user.getEmail()); 
			model.addAttribute("name", user.getName()); 
			model.addAttribute("picture", user.getPicture()); 
		}
		
		return "index";
	
	}
	
}
