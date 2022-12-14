package ncontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import service.CustomerService;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {

	private CustomerService customerservice;

	public CustomerService getCustomerservice() {
		return customerservice;
	}

	@Autowired
	public void setCustomerservice(CustomerService customerservice) {
		this.customerservice = customerservice;
	}

	//public List<Notice> getNotices(int page, String field, String query) 
	@RequestMapping("notice.htm")   //   /customer/notice.htm
	public String notices(String pg , String f , String q , Model model) {
		
		
		//DAO 작업
		List<Notice> list = customerservice.notices(pg, f, q); 		
		model.addAttribute("list", list);  //자동으로 notice.jsp forward 
		
		return  "customer/notice";
	}
	
	//public Notice getNotice(String seq)
	@RequestMapping("noticeDetail.htm")
	public String noticesDetail(String seq  , Model model) {
	
		Notice notice = customerservice.noticeDetail(seq);
		model.addAttribute("notice", notice);
		return "customer/noticeDetail";
		
		
	}
	
	//@GetMapping
	//@PostMapping
	//글쓰기 화면 보여주기(GET)
	//http://localhost:8090/SpringMVC_Basic04_WebSite_Annotation/customer/notice.htm
	//<a class="btn-write button" href="noticeReg.htm">글쓰기</a>  notice.jsp 에서
	
	
	
	//@RequestMapping(value="noticeReg.htm",  method = RequestMethod.GET)
	@GetMapping(value="noticeReg.htm")
	public String noticeReg() {
			return  "customer/noticeReg";
	}
	
	

	//글쓰기
	//@RequestMapping(value="noticeReg.htm",  method = RequestMethod.POST)
	@PostMapping(value="noticeReg.htm")
	public String noticeReg(Notice n , HttpServletRequest request) {
		String url = null;
		try {
			url = customerservice.noticeReg(n, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	

	//글 수정
	//@RequestMapping(value="noticeEdit.htm"  , method = RequestMethod.GET)
	@GetMapping(value="noticeEdit.htm")
	public String noticeEdit(String seq , Model model) {
		Notice notice=null;
		try {
		   notice = customerservice.noticeEdit(seq);
		} catch( Exception e) {
				e.printStackTrace();
		} 	
		model.addAttribute("notice", notice);	
		return "customer/noticeEdit";
	}
	
	
	

	//글 수정 처리하기
	//@RequestMapping(value="noticeEdit.htm"  , method = RequestMethod.POST)
	@PostMapping(value="noticeEdit.htm")
	public String noticeEdit(Notice n , HttpServletRequest request) {
		 return customerservice.noticeEdit(n, request);

	}
	
	@RequestMapping("noticeDel.htm")
	public String noticeDel(String seq) {
		return customerservice.noticeDel(seq);
		
	}
	
	//파일 다운로드
	@RequestMapping("download.htm")
	public void download(String p , String f , HttpServletRequest request , HttpServletResponse response) throws IOException {
		  customerservice.download(p, f, request, response);
	}
}
