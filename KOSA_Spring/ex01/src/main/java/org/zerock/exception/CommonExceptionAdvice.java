package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;


//@ControllerAdvice는 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시
//@ExceptionHandler는 해당 메서드가 () 들어가는 예외 타입을 처리함을 명시
//@ExceptionHandler 의 속성으로 Exception 클래스 타입을 지정할 수 있음
//Exception.class를 지정하였으므로 모든 예외 처리가 except()만을 이용하여 처리할 수 있다
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		
		log.error("Exception......" + ex.getMessage());
		model.addAttribute("exception", ex);
		log.error(model);
		return "sample/error-page";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		return "sample/custom404";
	}
}
