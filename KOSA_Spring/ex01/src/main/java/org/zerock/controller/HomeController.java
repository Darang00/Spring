package org.zerock.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class HomeController {
	//메인화면 보여주는거
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//6.5.2 (p145) String 타입
	//상황에 따라 다른 화면을 보여줄 필요가 있을 때 사용
	//String 타입에는 특별한 키워드를 붙여서 사용할 수 있다
	// redirect: 리다이렉트 방식으로 처리하는 경우
	// forward: 포워드 방식으로 처리하는 경우
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}