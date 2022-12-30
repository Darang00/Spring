package service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MemberDao;
import dto.Member;

@Service
public class JoinService {

	@Autowired
	private SqlSession sqlsession;
	
	public int insertMember(Member member) {
		int result = 0;
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		result = dao.insertMember(member);
		return result;
	}
	
	public int idCheck(String memberid) {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		int result = dao.idCheck(memberid);
		return result;
	}
	
	public int loginCheck(String name, String pwd) {
		MemberDao dao = sqlsession.getMapper(MemberDao.class);
		int result = dao.loginCheck(name, pwd);
		return result;
	}
}
