package dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="emplist")
public class EmpList {
	@XmlElement(name="emp")
	private List<Emp> emps;
	
	public EmpList() {
		
	}
	public EmpList(List<Emp> emps) {
		this.emps = emps;
	}
	
	public List<Emp> getEmps(){
		return emps;
	}
	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}

}
