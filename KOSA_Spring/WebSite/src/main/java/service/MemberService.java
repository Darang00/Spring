package service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import dto.Member;

@Service
public class MemberService {
	
	@Autowired
	private SqlSession sqlsession;
	
	public Member getMember(String userid) {
		System.out.println("getMember 서비스 탔다");
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		Member member = dao.memberDetail(userid);
		System.out.println("member 리턴할거다: " + member.toString());
		return member;
	}
	
	public void updateMember(Member member) {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		int result = dao.updateMember(member);
		if(result > 0) {
			System.out.println("업데이트 성공");
		}else {
			System.out.println("업데이트 실패");
		}
	}
}
