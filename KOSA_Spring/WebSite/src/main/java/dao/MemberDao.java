package dao;

import dto.Member;

public interface MemberDao {
	
	//회원가입
	public int insertMember(Member member);
	
	//아이디 체크
	public int idCheck(String userid);
	
	//로그인 체크
	public int loginCheck(String name, String pwd);
	
	//회원 상세
	public Member memberDetail(String userid);
	
	//회원 수정
	public int updateMember(Member member);

}
