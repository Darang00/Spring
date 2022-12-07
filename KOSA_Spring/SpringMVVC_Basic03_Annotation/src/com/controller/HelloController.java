package com.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/*
 이렇게 하면 요청 하나당 controller 하나 필요
 public class HelloController implements Controller
 방식은 
 단점: 서비스 요청 개수 만큼 controller 생성
 게시판: 목록보기 >> ListController
 	      글쓰기 >> WriteController
 	      수정하기 >> EditController

public class HelloController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
*/

/*
 @Controller
 public class HelloController
 
 method 단위로 매핑
 하나의 controller 안에 여러개의 함수를 생성해서 각각 매핑
 
  게시판: 목록보기 >> ListController >> public void list()
 	      글쓰기 >> WriteController >> public void write()
 	      수정하기 >> EditController >> public void edit()
 	      과 같이 일일이 controller 안만들어도 되고 함수로 대신 쓸 수 있다.
 
 */
@Controller
public class HelloController{
	public HelloController() {
		System.out.println("HelloController 생성자 호출");
	}
	
	
	@RequestMapping("/hello.do") //<a href="hello.co"></a> 요청이 오면 함수 실행
	public ModelAndView hello() {
		System.out.println("[hello.do] method call");
		ModelAndView mv = new ModelAndView();
		mv.addObject("greeting", getGreeting());
		mv.setViewName("Hello");
		return mv;
	}
	
	private String getGreeting() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String data="";
		if(hour >= 6 && hour <= 10) {
			data="학습시간";
		}else if(hour >=11 && hour <=13) {
			data="배고픈 시간";
		}else if(hour >= 14 && hour <= 18) {
			data="졸려운 시간";
		}else {
			data="go home";
		}
		return data;
	}
	
}
