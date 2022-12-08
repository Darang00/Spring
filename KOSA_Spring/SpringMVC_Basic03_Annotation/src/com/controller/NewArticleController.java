package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.NewArticleCommand;
import com.service.ArticleService;

/*
 -client 가 요청을 보냄
 1. 화면 보여줘라 (글쓰기, 로그인 화면): write.do
 2. 처리해 주세요 (글쓰기 입력 처리, 로그인 완료 처리): writeok.do
 
 요청 주소가: write.do >> 화면
 요청 주소가: writeok.do >> 처리.....
 
 - client 가 요청할 때 
 - 1개의 주소를 가지고 판단
 - 요청 주소 하나로 (화면을 보여달라는지, 처리 해달라는지) 판단 > 판단의 근거: 전송 방식(GET, POST)
 >> http://localhost:8090/SpringMVC_Basic03_Annotation/article/newArticle.do
 같은 주소 인데 
 전송 방식
 GET >> 화면 >> view 제공
 POST >> 서비스 처리 >> insert, update >> 화면
 */

@Controller
@RequestMapping("/article/newArticle.do")
public class NewArticleController {
	
	private ArticleService articleservice;
	
	@Autowired
	public void setArticleservice(ArticleService articleservice) {
		this.articleservice = articleservice;
	}
	
	//@RequestMapping(method = RequestMethod.GET)  //화면 보여주세요
	@GetMapping //5.x.x 버전부터
	public String form() {
		//******Spring 에서 >> 함수의 return 타입이 String 이면 view의 주소를 의미...
		System.out.println("GET");
		return "article/newArticleForm";
		//	/WEB-INF/views + article/newArticleForm + .jsp
	}
	
	//1. 데이터를 받는 가장 전통적인 방식 >> HttpServletRequest request >> 코드량 증가 >> spring에서 잘 안쓰는 방식
	//@RequestMapping(method = RequestMethod.POST) //처리해 주세요
	
	/* 호랑이 담배 피던 시절 코드
	@PostMapping //5.x.x 버전부터
	public ModelAndView submit(HttpServletRequest request) {
		System.out.println("POST");
		
		NewArticleCommand article = new NewArticleCommand();
		article.setParentId( Integer.parseInt(request.getParameter("parentId")));
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		
		
		//NewArticleController 가 service 필요해 
		this.articleservice.writeArticle(article);
		//처리완료
		
		ModelAndView mv = new  ModelAndView();
		mv.addObject("newArticleCommand", article);
		mv.setViewName("article/newArticleSubmitted");
		
		return mv;
	}
	*/
	
	/*
	 2. Spring 에서 parameter를 DTO 객체로 받기
	 2.1 자동화 >> 전체조건 >> input name="" 값이 DTO 객체의 member field명과 동일
	 */
	
	/*
	@PostMapping
	public ModelAndView submit(NewArticleCommand command) {
		//1. 자동으로 DTO 객체 생성 NewArticleCommand command = new NewArticleCommand()
		//2. 넘어온 parameter 값을 DTO 에 setter 함수를 사용해서 자동 주입
		//3. NewArticleCommand command 객체 IOC 컨테이너 안에 자동 생성 id="newArticleCommand"
		this.articleservice.writeArticle(command);
		//처리완료
		
		ModelAndView mv = new  ModelAndView();
		mv.addObject("newArticleCommand", command);
		mv.setViewName("article/newArticleSubmitted");
		
		return mv;
	}  */
	
	@PostMapping
	public String submit(@ModelAttribute("Articledata") NewArticleCommand command) {
		/*
		 ModelAndView mv = new  ModelAndView();
		 mv.addObject("newArticleCommand", command);
			>> @ModelAttribute("Articledata") 로 대체
			
		mv.setViewName("article/newArticleSubmitted");
			>> return "article/newArticleSubmitted"; 로 대체
			
	>>> view 까지 자동 forward....
		 */
		this.articleservice.writeArticle(command);		
		return "article/newArticleSubmitted";		
	}

}
