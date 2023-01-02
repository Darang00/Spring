package dto;

import java.util.Date;

import lombok.Data;

@Data
public class Member {
	
	private String userid;
	private String pwd;
	private String name;
	private String gender;
	private String phone;
	private String email;
	private Date joindate;
	

}
