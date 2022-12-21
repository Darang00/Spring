package kosa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kosa.vo.Employee;

@Controller
public class AjaxController {
	
	//return type이 Employee 인 함수
	@RequestMapping("response.kosa")
	public @ResponseBody Employee add(HttpServletRequest request, HttpServletResponse response) {
		//HttpServletRequest request, HttpServletResponse response를 parameter로 쓰는건 가장 전통적인 방법
		//'firstName=' + $("#firstName").val() + "&lastName=" + $("#lastName").val() + "&email=" + $("#email").val(),
		
		Employee employee = new Employee();
		employee.setFirstname(request.getParameter("firstName"));
		employee.setLastname(request.getParameter("lastName"));
		employee.setEmail(request.getParameter("email"));
		System.out.println(employee.toString());
		return employee; //Employee 타입의 객체의 주소 employee를 바로 return 했다.
		//우리가 원하는건 {"firstname":"a", "lastname":"b"...} 이런 형태로 client 로 보내는 것
	}
	
	//1.client 에서 전송된 데이터를 객체로 받기 :@RequestBody
	//2. 서버에서 client에 객체 전송하기          :@ResponseBody
	@RequestMapping("response2.kosa")
	public @ResponseBody Employee add(@RequestBody Employee emp) {
		System.out.println("response2.kosa");
		System.out.println(emp.toString());
		return emp;
	}
	
	//POINT JAVA API 객체도 가능합니다.
	@RequestMapping("response3.kosa")
	public @ResponseBody Map<String,Object> add(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result",  "data"); //"result" 에 "data"라는 문자열을 넣겠다
		map.put("hello", "world"); //"hello"에 "world"라는 문자열을 넣겠다. 
		//view 단 (index.jsp) 에서 함수 자원으로 data.result, data.hello 쓸 수 있음
		return map;
		//{"result":"data" , "hello":"world"} 
		//map.put("result", "hello":"world"}
		//map.put("result":"success") 활용... 프로젝트에서.
	}

}
