package kr.or.kosa;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IntroController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		System.out.println("introcontroller 요청이 실행: handleRequest 함수가 실행됐다");
		//doGet, doPost 의 역할 >> handleRequest 
		//ModelAndView : 데이터를 담거나 View 를 지정하는 객체
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", "hong");
		mav.setViewName("Intro");
		//InternalResourceViewResolver 에 의해서 view 주소가 조합
				// 	/WEB-INF/views/ + Intro + .jsp
				//	/WEB-INF/views/Intro.jsp
		
		return mav;
	}
	
	

}
