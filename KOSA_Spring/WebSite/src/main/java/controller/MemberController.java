package controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		System.out.println("memberUpdate GET 방식 서비스 탔다");
		Member member = memberService.getMember(principal.getName());
		model.addAttribute("member", member);
		return "member/memberUpdate"; // 경로 리턴할 때'.' 인지 '/' 인지 구분 잘하기ㅠㅠㅠ 
	}
	
	//수정페이지 처리
	@RequestMapping(value="memberUpdate.htm", method=RequestMethod.POST)
	public String memberUpdate(Model model, Member member, Principal principal) {
		System.out.println("memberUpdate POST 방식 서비스 탔다");
		Member updatemember = memberService.getMember(principal.getName());
		
		updatemember.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
		updatemember.setName(member.getName());
		updatemember.setGender(member.getGender());
		updatemember.setPhone(member.getPhone());
		updatemember.setEmail(member.getEmail());
		
		memberService.updateMember(updatemember);
		System.out.println("수정 완료");
		return "redirect:/index.htm";
	}
	
	//마이페이지 - 비밀번호 체크 화면 이동
	@GetMapping("mypage.htm")
	public String mypage(String pwd, Model model, Principal principal) {
		return "member/mypage";
	}
	
	//마이페이지 - 비밀번호 체크 화면 처리
		@PostMapping("mypage.htm")
		public String mypageCheck(String pwd, Model model, Principal principal) {
			Member mypageMember = memberService.getMember(principal.getName());
			String encodedPassword = mypageMember.getPwd();
			//pwd를 입력했다면 체크한 결과값, 입력하지 않았으면 false
			
			boolean result = (pwd != null) ? bCryptPasswordEncoder.matches(pwd, encodedPassword) : false;
			//result에 담기는 것 = pwd가 null이 아니면 bCryptPasswordEncoder.matches(pwd, encodedPassword)로 
			//     			   pwd가 encodedPassword랑 같은지 확인해서 같으면 true, 다르면 false 리턴함 (bCryptPasswordEncoder.maches는 boolean 타입이니까)
			//                 pwd가 null이면 false를 result에다가 할당
			
			String url = result ? "redirect:/member/memberUpdate.htm" : "/mypage.htm"; //3항 연산자 ? 앞에 꺼(result)가 true면 : 앞에 있는 "redirect:memberupdate.htm" 이 뷰페이지 가고 false면 : 뒤에 있는 "mypage.htm" 이게 실행된다. 
			//url에 담기는 것 = result가 참이면 "redirect:memberUpdate.htm"를, 거짓이면 "mypage.htm"을 리턴한다
			
			return url;	
		}
	
	
	
}
