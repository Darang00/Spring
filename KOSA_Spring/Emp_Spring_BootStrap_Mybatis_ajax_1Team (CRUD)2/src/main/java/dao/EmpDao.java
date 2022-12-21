package dao;

import java.util.List;

import dto.Emp;

public interface EmpDao {
	List<Emp> getEmpList();
	int deleteEmp(String empno);
	int updateEmp(Emp dto);
	Emp selectEmp(String empno);
	int inisertEmp(Emp dto);
	
}
