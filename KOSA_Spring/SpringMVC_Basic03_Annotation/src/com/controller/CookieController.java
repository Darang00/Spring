package com.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	
	@RequestMapping("/cookie/make.do")
	public String make(HttpServletResponse response) {
		response.addCookie(new Cookie("auth", "1004")); //Cookie도 객체라서 new 해줘야 한다 //servlet 동일
		return "cookie/CookieMake"; //view의 주소 return
	}
	
	//public String view(HttpServletRequest request) 전통
	@RequestMapping("/cookie/view.do")
	public String view(@CookieValue(value="auth", defaultValue="1007") String auth) {
		System.out.println("Value of Cookie from client: " + auth);
		return "cookie/CookieView";
	}
	
}
