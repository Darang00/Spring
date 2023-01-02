package controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dto.Member;
import service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value="login.htm", method=RequestMethod.GET)
	public String memberConfirm() {
		return "member/login";
	}
	
	//로그인 처리
	@RequestMapping(value="login.htm", method=RequestMethod.POST)
	public String memberConfirm(@RequestParam("pwd") String rawPassword, Principal principal) {
		System.out.println("로그인 처리 컨트롤러 탔다");
		String viewpage="";
		
		System.out.println("principal: "+principal);
		
		//회원 정보
		Member member = memberService.getMember(principal.getName());
		
		//DB에서 가져온 암호회된 문자열
		String encodedPassword = member.getPwd();
		
		System.out.println("rowPassword: " +rawPassword );
		System.out.println("encodedPassword: " + encodedPassword);
		
		boolean result = bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
		
		if(result) {
			System.out.println(result);
			viewpage="redirect:index.htm";
		}else {
			System.out.println(result);
			viewpage="redirect:memberConfirm.htm";
		}
		return viewpage;
	}
	
	//수정 페이지 이동
	@RequestMapping(value="memberUpdate.htm", method=RequestMethod.GET)
	public String memberUpdate(Model model, Principal principal) {
		Member member = memberService.getMember(principal.getName());
		model.addAttribute("member", member);
		return "join.memberUpdate";
	}
	
	//수정페이지 처리
	@RequestMapping(value="memberUpdate.htm", method=RequestMethod.POST)
	public String memberUpdate(Model model, Member member, Principal principal) {
		Member updatemember = memberService.getMember(principal.getName());
		
		updatemember.setName(member.getName());
		updatemember.setPhone(member.getPhone());
		updatemember.setEmail(member.getEmail());
		updatemember.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
		memberService.updateMember(updatemember);
		return "redirect:/index.htm";
	}
	
	//마이페이지 - 비밀번호 체크 화면 이동
	@GetMapping("mypage.htm")
	public String mypage(String pwd, Model model, Principal principal) {
		Member member = memberService.getMember(principal.getName());
		String encodedPassword = member.getPwd();
		//pwd를 입력했다면 체크한 결과값, 입력하지 않으면 false
		boolean result = (pwd !=null) ? bCryptPasswordEncoder.matches(pwd, encodedPassword) : false;
		String  url = result ? "redirect:memberUpdate.htm" : "mypage.htm"; //3항 연산자
		return null;
	}
	
	
}
