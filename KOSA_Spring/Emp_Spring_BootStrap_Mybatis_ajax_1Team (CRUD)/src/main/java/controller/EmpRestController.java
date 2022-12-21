package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.EmpService;

@RestController //@Controller + @ResponseBody
public class EmpRestController {
//	
//	@Autowired
//	private EmpService empservice;
//	
//	@RequestMapping(value="view.ajax")
//	public String ViewPage() { //converter 에 의해서 문자열 전달
//		System.out.println("view.ajax");
//		return "view.jsp 문자열 리턴";
//	}
}
