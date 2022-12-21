package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import dto.Emp;
import service.EmpService;

@Controller
public class EmpController {
	
	@Autowired
	private View jsonview;
	
	@Autowired
	private EmpService empservice;
	
	//전체조회
	@RequestMapping(value="json.ajax")
	public View jsonkosa(Model model) {
		List<Emp> list = empservice.getEmpList();
		model.addAttribute("emp", list);
		return jsonview; //private View jsonview 타입으로 리턴
	}
	
	//삭제(delete)
	@RequestMapping(value="delete.ajax")
	public View delete(String empno, Model model) {
		empservice.deleteEmp(empno);
		List<Emp> list = empservice.getEmpList();
		model.addAttribute("emp", list);
		return jsonview;
	}
	
	//수정(update GET)
		@RequestMapping(value="delete.ajax", method = RequestMethod.GET)
		public View update(String empno, Model model) {
			model.addAttribute("emp", empservice.selectEmp(empno));
			return jsonview;
		}
		
		//수정(update POST)
		@RequestMapping(value="update.ajax", method = RequestMethod.POST)
		public View update(Emp emp, Model model) {
			empservice.updateEmp(emp);
			List<Emp> list = empservice.getEmpList();
			model.addAttribute("emp", list);
			return jsonview;
		}
		
		//추가 (insert)
		@RequestMapping(value="insert.ajax", method = RequestMethod.POST)
		public View insert(Emp emp, Model model) {
			empservice.insertEmp(emp);
			List<Emp> list = empservice.getEmpList();
			model.addAttribute("emp", list);
			return jsonview;
		}
}
