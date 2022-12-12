package ncontroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {

	private NoticeDao noticedao;
	
	
	@Autowired
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}

	/*
	1. method안에서 return type  [String] 리턴값이 뷰의 주소
	2. public ModelAndView notices ...    >  ModelAndView 객체 생성  > 데이터 , 뷰 설정 > return 
	3. public String notices (Model model) { 함수가 실행시 내부적으로 Model객체의 주소가 들어온다  }
	
	*/
	
	//public List<Notice> getNotices(int page, String field, String query) 
	@RequestMapping("notice.htm")   //   /customer/notice.htm
	public String notices(String pg , String f , String q , Model model) {
		
		//default 값 설정
		int page = 1;
		String field="TITLE";
		String query = "%%";
		
		if(pg != null   && ! pg.equals("")) {
			page  = Integer.parseInt(pg);
		}
		
		if(f != null   && ! f.equals("")) {
			field = f;
		}

		if(q != null   && ! q.equals("")) {
			query = q;
		}
		
		//DAO 작업
		List<Notice> list = null;
		try {
						list = noticedao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
					e.printStackTrace();
		} catch (SQLException e) {
					e.printStackTrace();
		}
		
		
		//Spring  적용
		/*
		ModelAndView   mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("notice.jsp");
		return mv;
		*/
		model.addAttribute("list", list);  //자동으로 notice.jsp forward 
		/*
		  	<c:forEach items="${list}" var="n"> ...
		*/
		return  "notice.jsp";
	}
	
	//public Notice getNotice(String seq)
	@RequestMapping("noticeDetail.htm")
	public String noticesDetail(String seq  , Model model) {
	
		Notice notice = null;
		try {
					notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
						e.printStackTrace();
		} catch (SQLException e) {
						e.printStackTrace();
		}
		
		/*
		ModelAndView  mv = new ModelAndView();
		
		mv.addObject("notice", notice);
		mv.setViewName("noticeDetail.jsp");
		*/
		model.addAttribute("notice", notice);
		return "noticeDetail.jsp";
	}
	
	
	//@GetMapping
	//@PostMapping
	//글쓰기 화면 보여주기(GET)
	//http://localhost:8090/SpringMVC_Basic04_WebSite_Annotation/customer/notice.htm
	//<a class="btn-write button" href="noticeReg.htm">글쓰기</a>  notice.jsp 에서
	@GetMapping(value="noticeReg.htm") //return Type이 String이면 return은 view의 주소
	public String noticeReg() {	
		return "noticeReg.jsp";
	}
	
	//글쓰기 처리(POST)
	//Notice  DTO 활용 
	//import org.springframework.web.multipart.commons.CommonsMultipartFile; 활용하기
	//upload 폴더는  request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로 
	@PostMapping("noticeReg.htm")
	public String WriteOk(Notice notice, HttpServletRequest request) { //파일 객체를 가질 수 있는 변수가 Notice에 있으니까 DTO 객체를 매개변수로 받았다
		System.out.println(notice.toString());
		
		CommonsMultipartFile imagefile=notice.getFile(); //업로드한 파일 정보
		
		//POINT: DB에 들어갈 파일 명 추출
		notice.setFileSrc(imagefile.getName()); //getName()은 CommonsMultipartFile 객체가 가지고 있는 함수: 파일의 이름을 가져오는 함수
		String filename = imagefile.getOriginalFilename();
		String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로 (저장 경로_RealPath 하면 C드라이브에서 부터 경로 타고 옴)
		String fpath = path + "\\" + filename;
		System.out.println(fpath);
		
		FileOutputStream fs =null;
		try {
			     fs = new FileOutputStream(fpath);
			     fs.write(imagefile.getBytes());
			     
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		notice.setFileSrc(imagefile.getName());
		//notice.setFileSrc(filename); getName()은 input 테그의 이름이다
		
		try {
			noticedao.insert(notice);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return "redirect:notice.htm";
	}


	//private String fileSrc; 업로드 파일명
	//noticedao.insert() 함수사용 
	
	

	//글쓰기 처리 한다음 .... 
	//Servlet , jsp : location.href or  response.sendRedirect 
	//같은 기능을 스프링에서는 
	// return "redirect:notice.htm"	​

	//글 수정하기 (화면이면서 데이터 처리) GET
	//noticeEdit.htm
	//글번호 받기와     (String seq , Model model) 사용
	//noticeDetail.jsp 아래부분 링크 수정하기
	//<a class="btn-edit button" href="noticeEdit.jsp">수정</a>
	//<a class="btn-del button" href="noticeDel.jsp">삭제</a>
	@GetMapping("noticeEdit.htm")
	public String noticeEdit(String seq, Model model) { //기존에 적혀있던 내용을 받아와야 하는 함수
		Notice notice = null;
		System.out.println(seq);
		try {
					notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
						e.printStackTrace();
		} catch (SQLException e) {
						e.printStackTrace();
		}
		System.out.println(notice);
		model.addAttribute("notice", notice);
		
		return "noticeEdit.jsp";
	}
	
	
	

	//글 수정 처리하기 (POST)
	//글 삽입하기와 동일한 처리
	//글 수정하기 처리가 끝나면
	// return 화면상세로 이동   redirect:noticeDetail.htm?글번호가지고 .... 
	@PostMapping("noticeEdit.htm")
	public String noticeEditOK(Notice notice, HttpServletRequest request , Model model) { //수정해서  insert 하고 목록 뿌리는 함수 
	 //HttpServletRequest 이거로 파일 처리
		System.out.println("edit");
		System.out.println(notice.toString());
		
		CommonsMultipartFile imagefile=notice.getFile(); //업로드한 파일 정보
		
		//POINT: DB에 들어갈 파일 명 추출
		notice.setFileSrc(imagefile.getName()); //getName()은 CommonsMultipartFile 객체가 가지고 있는 함수: 파일의 이름을 가져오는 함수
		String filename = imagefile.getOriginalFilename();
		String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로 (저장 경로_RealPath 하면 C드라이브에서 부터 경로 타고 옴)
		String fpath = path + "\\" + filename;
		System.out.println(fpath);
		
		FileOutputStream fs =null;
		try {
			     fs = new FileOutputStream(fpath);
			     fs.write(imagefile.getBytes());
			     
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		notice.setFileSrc(filename);
		//notice.setFileSrc(filename); getName()은 input 테그의 이름이다
		
		try {
			System.out.println(noticedao.update(notice));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("seq", notice.getSeq());
		
		return "noticeDetail.htm";
	}
	
	
	
}
