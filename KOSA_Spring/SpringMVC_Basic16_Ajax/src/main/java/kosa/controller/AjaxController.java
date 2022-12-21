package kosa.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import kosa.vo.Employee;
import kosa.vo.TestVO;

@Controller
public class AjaxController {
	
	//org.springframework.web.servlet.view.json.MappingJackson2JsonView
	//View 인터페이스 부모타입
	@Autowired
	private View jsonview;
	
	//command=AjaxTest&name=java&arr="+array
	@RequestMapping("json.kosa")
	public View jsonkosa(String command, String name, String [] arr, ModelMap map) {
		
		System.out.println("command: " + command);
		System.out.println("name: " + name);
		System.out.println("arr: " + arr);
		System.out.println("Arrays.toString(): " + Arrays.toString(arr));
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("chiken");
		list.add("beer");
		list.add("pizza");
		
		map.addAttribute("menu", list); //View 단에 전달
		
		
		return jsonview;
		//<bean name="jsonview" 객체와 동일한 이름 (servlet-context.xml 에)
		//map.addAttribute("menu", list); 자원을 자동으로 json 객체로 변환해서 클라이언트에게 비동기 전달
	}
	
	@RequestMapping("json2.kosa")
	public View jsonkosa(String command, String name, ModelMap map) {
		System.out.println("command: " + command);
		System.out.println("name: " + name);
		
		List<TestVO> list = new ArrayList<TestVO>(); //객체 배열
		
		TestVO vo = new TestVO();
		vo.setBeer("RAGAR");
		vo.setFood("SNAIL");
		
		list.add(vo);
		
		TestVO vo2 = new TestVO();
		vo2.setBeer("CASS");
		vo2.setFood("CHICKEN");
		
		list.add(vo2);
		
		map.addAttribute("menu", list);
		/* JSON 객체로 바꾸는거
		 data = {"menu":[{"beer":"RAGER"}, {"food":"SNAIL"}, {}]}

		 */
		
		return jsonview;
		
	}
	
	//Employee 객체를 2개 만들어서 list 형태로 반환해라
	@RequestMapping("json3.kosa")
	public View jsonkosa(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		
		List<Employee> employeeList = new ArrayList<Employee>(); //객체 배열
		
		Employee eList = new Employee();
		eList.setFirstname("DAYEONG");
		eList.setLastname("LHO");
		eList.setEmail("gmail.com");
		
		employeeList.add(eList);

		Employee eList2 = new Employee();
		eList2.setFirstname("HEY");
		eList2.setLastname("LEE");
		eList2.setEmail("naver.com");
		
		employeeList.add(eList2);

		
		map.addAttribute("data", employeeList);
		/* JSON 객체로 바꾸는거
		data ={"data":[{"firstname":"DAYEONG"}, {"lastname":"LHO"}, {}]}

		 */
		
		return jsonview;
	}
	
	@RequestMapping("json4.kosa")
	public void typeFunction(@RequestParam(value="aaa[]", required=false) String[] aaa,
							@RequestParam(value="bbb[]", required=false) String[] bbb,
							String ccc) {
		for(String str : aaa) {
			System.out.println(str);
		}
		System.out.println(bbb);
		System.out.println(ccc); //문자열 (객체 형태 문자열) >> 객체로 전환
		
	}
	
	
	

}
