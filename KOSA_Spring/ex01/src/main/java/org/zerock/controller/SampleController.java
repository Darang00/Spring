package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTODateTimeFormat;
import org.zerock.domain.TodoDTOInitBinder;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void baisc() {
		log.info("basic..........................");
		
	}
	
	//6.2 (p128) @RequestMapping의 변화
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet1() { 
		log.info("basic get ..........................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() { //오직 GET 방식에서만 사용 가능
		log.info("basic get only get..................");
	}
	
	//6.3 (p129) Controller의 파라미터 수집
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		//브라우저 URL; http://localhost:8090/sample/ex01?name=AAA&age=10  
		log.info("" + dto);
		//결과; INFO : org.zerock.controller.SampleController - SampleDTO(name=AAA, age=10)
		
		return "ex01";
	}
	
	//6.3.1 (p131) 파라미터의 수집과 변환
	@GetMapping("/ex02")
	public String ex02(@RequestParam("paramName") String name, @RequestParam("paramAge") int age) {
		//@RequestParam("안에 들어가는 변수")는 브라우저의 URL 에 붙은 이름이다. 이 경우
		//http://localhost:8090/sample/ex02?paramName=AAA&paramAge=10 이렇게 쓰면 paramName에 들어간 AAA가 name 변수에, paramAge에 들어간 10이 age 변수에 들어간다
		//즉 @RequestParam은 파라미터로 사용된 변수의 이름과 전달되는 파라미터의 이름이 다를 때 사용할 수 있다.
		log.info("paramName: " + name); //INFO : org.zerock.controller.SampleController - paramName: AAA
		log.info("paramAge: " + age); //INFO : org.zerock.controller.SampleController - paramAge: 13
		
		return "ex02";
	}
	
	//6.3.2 (p132) 리스트, 배열 처리
	//동일한 이름의 파라미터가 여러 개 전달되는 경우 ArrayList<> 등을 이용해서 처리 가능
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ListIds") ArrayList<String> ids) {
		//브라우저 URL: http://localhost:8090/sample/ex02List?ListIds=111&ListIds=222&ListIds=333
		log.info("ids: " + ids); 
		//결과; INFO : org.zerock.controller.SampleController - ids: [111, 222, 333]
		
		return "ex02List";
	}
	//스프링은 파라미터의 타입을 보고 객체를 생성하므로 파라미터의 타입은 List<>와 같이 인터페이스 타입이 아닌 실제적인 클래스 타입으로 지정한다.
	
	//배열의 경우에도 동일하게 처리 가능
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("arrayIds") String[] ids) {
		//브라우저 URL: http://localhost:8090/sample/ex02Array?arrayIds=10&arrayIds=20&arrayIds=30
		log.info("array ids: " + Arrays.toString(ids));
		//결과; INFO : org.zerock.controller.SampleController - array ids: [10, 20, 30]

		return "ex02Array";
	}
	
	//6.3.3 객체 리스트 (p133) 객체 리스트
	//전달하는 데이터가 SampleDTO와 같이 객체 타입이고 여러 개를 처리해야 하는 경우
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		//브라우저 URL; http://localhost:8090/sample/ex02Bean?sampleDTOList%5B0%5D.name=aaa&sampleDTOList%5B1%5D.name=BBB&sampleDTOList%5B2%5D.name=CCC
		//브라우저 URL에서 받는 변수 이름은 SampleDTOList 클래스의 멤버필드 이름과 맞춰야 한다.
		log.info("list dtos: " + list);
		//결과; INFO : org.zerock.controller.SampleController - list dtos: SampleDTOList(sampleDTOList=[SampleDTO(name=aaa, age=0), SampleDTO(name=BBB, age=0), SampleDTO(name=CCC, age=0)])
		//결과적으로 3개의 SampleDTO 객체가 생성되었고, '[]'안에 인덱스 번호에 맞게 객체의 속성값이 세팅되었다.
		return "ex02Bean";
	}
	
	//6.3.4 (p135) @InitBinder
	//파라미터의 수집을 다른 용어로 'binding(바인딩)'이라고 한다. 
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/ex03InitBinder")
	public String ex03InitBinder(TodoDTOInitBinder todo) {
		//브라우저 URL; http://localhost:8090/sample/ex03InitBinder?title=test&dueDate=2023-01-10
		log.info("todo: " + todo);
		
		//결과; INFO : org.zerock.controller.SampleController - todo: TodoDTO(title=test, dueDate=Tue Jan 10 00:00:00 KST 2023)
		return "ex03InitBinder";
	}
	
	//6.3.5 (p138) @DataTimeFormat
	//@InitBinder를 이용해서 날짜를 변환할 수도 있지만, 파라미터로 사용되는 인스턴스 변수에 @DateTimeFormat을 적용해도 변환이 가능하다
	//따라서 @DateTimeFormat을 이요하는 경우에는 @InitBinder는 필요하지 않다
	@GetMapping("/ex03DateTimeFormat")
	public String ex03DateTimeFormat(TodoDTODateTimeFormat todo) {
		//브라우저 URL; http://localhost:8090/sample/ex03DateTimeFormat?title=test&dueDate=2023-05-28
		log.info("todo: " + todo);
		
		//결과; INFO : org.zerock.controller.SampleController - todo: TodoDTODateTimeFormat(title=test, dueDate=Sun May 28 00:00:00 KST 2023)
		return "ex03DateTimeFormat";
	}
	
	//6.4 (p139) Model이라는 데이터 전달자
	//Controller의 메서드를 작성할 때는 특별하게 Model이라는 타입을 파라미터로 지정할 수 있다.
	//Model 객체는 JSP에 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할
	//JSP와 같은 뷰(View)로 전달해야 하ㅡㄴ 데이터를 담아서 보낼 수 있다.
	//메서드의 파라미터에 Model 타입이 지정된 경우 스프링은 특별히 Model타입의 객체를 만들어서 메서드에 주입한다.
	//Model을 사용해야 하는 경우는 주로 Controller에 전달된 데이터를 이용해서 추가적인 데이터를 가져와야 하는 상황
	
	
	//6.4.1 @ModelAttribute 어노테이션
	//스프링 MVC의 기본 자료형
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, int page) {
		log.info("dto: " + dto );
		log.info("page: " + page);
		
		return "/sample/ex04";
	}
	
	//ex04()함수는 SampleDTO 타입과 init 타입의 데이터를 파라미터로 사용한다.
	//결과를 확인하기 위새 '/WEB-INF/views' 폴더 아래 sample 폴더 생성 후 리턴값에서 사용한 'ex04'에 해당하는 ex04.jsp를 작성한다
	//http://localhost:8090/sample/ex04?name=dayeong&age=23&page=9
	//화면에 SampleDTO만이 전달되었다. 브라우저 URL에 입력하는 값은 다 문자로 인식되므로 int 타입으로 선언된 page는 전달되지 않는다
	
	//@ModelAttribute는 강제로 전달받은 파라미터를 Model에 담아서 전달하도록 할 때 필요한 어노테이션이다
	//@ModelAttribute가 걸린 파라미터는 타입에 관계없이 무조건 Model에 담아서 전달되므로, 파라미터로 전달된 데이터를 다시 화면에서 사용해야 할 경우 유용하게 사용된다.
	@GetMapping("/ex04ModelAttiribute")
	public String ex04ModelAttribute(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto: " + dto);
		log.info("page: " + page);
		return "/sample/ex04";
	}
	//@ModelAttribute가 붙은 파라미터는 화면까지 전달되므로 브라우저를 통해서 호출하면 ${page}가 출력되는 것을 확인할 수 있다.
	//기본 자료형에 @ModelAttribute 를 적용할 경우에는 반드시 @ModelAttribute("page")와 같이 값(value)을 지정하도록 한다.
	
	//6.4.2 (p143) RedirectAttribute
	//@RedirectAttribute는 일회성으로 데이터를 전달하는 용도로 사용된다.
	//RedirectAttributes는 Model과 같이 파라미터로 선언해서 사용하고, addFlashAttribute(이름, 값) 메서드를 이용해서 화면에 한 번만 사용하고 다음에는 사용되지 않는 데이터를 전달하기 위해 사용
	
	//6.5 (p143) Controller의 리턴 타입
	//스프링 MVC의 구조에서 Controller의 메서드가 사용할 수 있는 리턴타입
	//String: jsp를 이용하는 경우에는 jsp파일의 경로와 파일이름을 나타내기 위해 사용
	//void: 호출하는 URL과 동일한 이름의 jsp를 의미한다
	//VO, DTO 타입: 주로 JSON 타입의 데이터를 만들어서 반환하는 용도로 사용
	//ResponseEntity 타입: response 할 때 Http 헤더 정보와 내용을 가공하는 용도로 사용
	//Model, ModelAndView: Model로 데이터를 반환하거나 화면까지 같이 지정하는 경우에 사용
	//HttpHeaders: 응답에 내용 없이 Http 헤더 메시지만 전달하는 용도로 사용
	
	//6.5.1 (p144) void 타입
	//메서드의 리턴 타입을 void로 지정하는 경우 일반적인 경우에는 해당 URL의 경로를 그대로 jsp 파일의 이름으로 사용
	@GetMapping("/ex05")
	public void ex05() {
		//브라우저 URL; http://localhost:8090/sample/ex05
		log.info("/ex05..........");
	}
	
	//6.5.2 (p145) String 타입
	//상황에 따라 다른 화면을 보여줄 필요가 있을 때 사용
	//HomeController에서 시연함
	
	//6.5.3 (p146) 객체 타입
	//Controller의 메서드 리턴 타입을 VO(Valule Object)나 DTO(Data Transfer Object) 타입 등 복합적인 데이터가 들어간 객체 타입으로 지정 가능
	//이 경우 주로 JSON 데이터를 만들어 내는 용도로 사용
	//이를 위해 jackson-databind 라이브러리르 pom.xml에 추가했다.
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		//브라우저 URL; http://localhost:8090/sample/ex06
		log.info("/ex06......................");
		SampleDTO dto = new SampleDTO();
		dto.setAge(23);
		dto.setName("Dayoeng");
		
		return dto;
	}
	
	//6.5.4 ResponseEntity 타입
	//ResponseEntity 를 통해서 원하는 헤더 정보나 데이터를 전달할 수 있다.
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07................");
		
		//{"name":"Dayoeng"}
		String msg = "{\"name\": \"Dayeong\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8" );
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	//6.5.5 파일 업로드 처리
	//commons-fileupload 라이브러리를 pom.xml에 추가
	@GetMapping("/exUpload")
	public String exUpload() {
		log.info("/exUpload...............");
		return "sample/exUpload"; //exUpload.jsp 페이지를 띄우는 함수
	}
	
	@PostMapping("/exUploadPost") //exUpload.jsp 에서 담은 파일들을 submit 하면 이 함수로 처리된다
	public String exuploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file -> {
			log.info("-------------------------------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		});
		
		return "sample/exUploadPost";
	}
	
	//6.6 Controller의 Exception 처리
	//스프링 MVC에서 예외 처리
	//@ExceptionHandler와 @ControllerAdvice를 이용한 처리
	//@RespnseEntity 	를 이용한 예외 메시지 구성
	//@ControllerAdvice는 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시
	//@ExceptionHandler는 해당 메서드가 () 들어가는 예외 타입을 처리함을 명시
	//@ExceptionHandler 의 속성으로 Exception 클래스 타입을 지정할 수 있음
	
	
}
