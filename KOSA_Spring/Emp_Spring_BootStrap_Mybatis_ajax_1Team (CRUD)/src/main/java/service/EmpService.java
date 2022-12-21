package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.EmpDao;
import dto.Emp;

@Service
public class EmpService {
	
	@Autowired
	private SqlSession sqlsession;
	
	//전체조회
	public List<Emp> getEmpList(){
		List<Emp> result = new ArrayList<Emp>();
		EmpDao dao = sqlsession.getMapper(EmpDao.class);
		result = dao.getEmpList();
		return result;
	}
	
	//삭제
	public int deleteEmp(String empno) {
		EmpDao empDao = sqlsession.getMapper(EmpDao.class);
		System.out.println(empDao);
		return empDao.deleteEmp(empno);
	}
	
	//수정
	public int updateEmp(Emp emp) {
		EmpDao empDao = sqlsession.getMapper(EmpDao.class);
		System.out.println(empDao);
		return empDao.updateEmp(emp);
	}
	
	//아마도.. 검색?
	public Emp selectEmp(String empno) {
		EmpDao empDao = sqlsession.getMapper(EmpDao.class);
		System.out.println(empDao);
		return empDao.selectEmp(empno);
	}
	
	//추가등록 (삽입)
	public int insertEmp(Emp emp) {
		EmpDao empDao = sqlsession.getMapper(EmpDao.class);
		return empDao.inisertEmp(emp);
	}
	

}
