package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;

import dto.Member;
import service.JoinService;

@Controller
@RequestMapping("/join/")
public class JoinController {
	
	@Autowired
	private JoinService joinService;
	
	///////////////////////////////
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	////////////////////////////////
	//회원가입 창 이동 //
	@RequestMapping(value="join.htm", method=RequestMethod.GET)
	public String join() {
		System.out.println("노노노노노");
		return "member/join";
	}
	
	//회원가입 처리
	@RequestMapping(value="join.htm", method=RequestMethod.POST)
	public String join(Member member) {
		System.out.println(member.toString());
		int result = 0;
		String viewpage="";
		
		member.setPwd(this.bCryptPasswordEncoder.encode(member.getPwd()));
		
		result = joinService.insertMember(member);
		if(result >0) {
			System.out.println("삽입 성공");
			viewpage="redirect:/index.htm";
		}
		return viewpage;
	}
	
	@RequestMapping(value="idcheck.htm", method=RequestMethod.POST)
	public View idCheck(@RequestParam("memberid") String memberid, Model model) {
		int result = joinService.idCheck(memberid);
		if(result >0) {
			System.out.println("아이디 중복");
			model.addAttribute("result", "fail");
		} else {
			System.out.println("삽입 실패");
			model.addAttribute("result", "success");
		}
		System.out.println("done of idcheck");
		return null;
	}
	
	//로그인 창??
	@RequestMapping(value="login.htm", method=RequestMethod.GET)
	public String login() {
		return "member/login";
	}
}
