package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
	
	/*
	 1. 전통적인 방법
	 public ModelAndView searchExternal(HttpServletRequest request){
	 	String id = request.getParameter("id")
	 }
	 
	 2. DTO 객체를 통한 전달 방법(게시판, 회원가입 데이터) *** 제일 많이 쓴다
	 public ModelAndView searchExternal(MemberDto member){
	 	/search/external.do?id=hong&name=김유신&age=100
	 	2.1 DTO에 있는 member field 이름이 >>
	 	private String id;
	 	private String name;
	 }
	 
	 3. 가장 편한 방법
	 public ModelAndView searchExternal(String id, String name, int age){
	 	/search/external.do?id=hong&name=김유신&age=100
	 	>> 함수의 parameter가 '~.do? " 다음에 오는 memberfield 이름과 같으면 자동 mapping 된다
	 }
	 
	 4. @RequestParam annotation 사용하기
	 4.1 유효성 처리
	 4.2 기본값 처리
	 
	 5. REST 방식 (비동기 처리) >> method=GET || POST || PUT || DELETE
	 @PathVariable >> /member/{memberid} >> /member/100
	 
	 100 추출해서 parameter로 쓴다.
	 
	 */
	
	@RequestMapping("/search/external.do")
	public ModelAndView searchExternal(@RequestParam(value="query", defaultValue="kosa") String query,
										@RequestParam(value="p" , defaultValue="10") int p) {
		System.out.println("param: " + query );
		System.out.println("param p: " + p);
		return new ModelAndView("search/external"); // public String searchExternal("view의 주소(경로)")
	}
	
}
