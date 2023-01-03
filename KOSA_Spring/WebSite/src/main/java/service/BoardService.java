package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.BoardDao;
import dto.Board;

@Service
public class BoardService {
	/*
	//게시물 개수
	int getBoardCount(String field, String query) throws ClassNotFoundException, SQLException;
	
	//전체 게시물
	List<Board> boardList(int page, String field, String query) throws ClassNotFoundException, SQLException;
	
	//게시물 삭제(delete)
	int boardDelete(String seq) throws ClassNotFoundException, SQLException;
	
	//게시물 수정(update)
	int boardUpdate(Board board) throws ClassNotFoundException, SQLException;
	
	//게시물 상세
	Board boardDetail(String seq) throws ClassNotFoundException, SQLException;
	
	//게시물 입력(insert)
	int boardWrite(Board n) throws ClassNotFoundException, SQLException; 
	 
	 */
	
	//Mybatis 직업
	private SqlSession sqlsession;
	
	@Autowired
	public  void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	//글목록보기 서비스(DB)
	public List<Board> boardList(String pg, String f, String q){
		
		//default 값 설정
		int page = 1;
		String field = "TITLE";
		String query = "%%";
		
		if(pg !=null && ! pg.contentEquals("")) {
			page = Integer.parseInt(pg);
		}
		
		if(f != null && ! f.contentEquals("")) {
			field = f;
		}
		
		if(q !=null && ! q.contentEquals("")) {
			query = q;
		}
		
		//DAO 작업
		List<Board> boardList = null;
		try {
		///동기화/////////////////////////////
		BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
		/////////////////////////////////////
		boardList = boardDao.boardList(page, field, query);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}
	
	//글 상세보기 서비스
	public Board boardDetail(String seq) {
		Board board = null;
		try {
			//동기화////
			BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
			///////////
			board = boardDao.boardDetail(seq);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return board;
	}
	
	//글쓰기 처리 서비스
	public String boardWrite(Board board, HttpServletRequest request, Principal principal) {
		List<CommonsMultipartFile> files = board.getFiles();
		List<String> filenames = new ArrayList<String>(); //파일명 에러
		
		if(files !=null && files.size() > 0) { //1개라도 업로드 된 파일이 존재하면
			for(CommonsMultipartFile multifile : files) { //이건 뭔 문법일까 >> 개선된 for 문: files가 List 타입이므로 리스트 돌면서 뽑아내는거
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/board/upload"); //배포된 서버 경로
				String fpath = path + "\\" + filename;
				System.out.println(fpath);
				
				if(!filename.contentEquals("")) { //실 파일 업로드 (웹서버)
					FileOutputStream fs = null;
					try {
						fs = new FileOutputStream(fpath);
						fs.write(multifile.getBytes());
						filenames.add(filename); //db에 입력될 파일명
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						try {
							fs.close();
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			}			
		} 
		//인증되면 인증 객체의 정보를 받는다.
		board.setWriter(principal.getName().trim());
		//파일명 (DTO)
		board.setFilesrc(filenames.get(0));
		board.setFilesrc2(filenames.get(1));

		try {
			//동기화
			BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
			boardDao.boardWrite(board); //DB insert
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:boardList.htm";
	}
	
	
	//글 삭제하기 처리 서비스
	public String boardDelete(String seq) {
		BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
		try {
			boardDao.boardDelete(seq);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "redirect:boardList.htm";
	}
	
	//글 수정하기 서비스
	public Board boardUpdate(String seq) {
		Board board = null;
		try {
			//동기화
			BoardDao boardDao =sqlsession.getMapper(BoardDao.class);
			board = boardDao.boardDetail(seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	
	//글 수정하기 처리 서비스
	public String boardUpdate(Board board, HttpServletRequest request) {
		List<CommonsMultipartFile> files = board.getFiles();
		List<String> filenames = new ArrayList<String>(); //파일명 관리
		
		if(files != null && files.size() > 0) { //1개라도 업로드된 파일이 존재하면
			for(CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/board/upload"); //배포된 서버 경로
				String fpath = path + "\\" + filename;
				System.out.println(fpath);
				
				if(!filename.equals("")) { //실 파일 업로드 (웹서버)
					FileOutputStream fs = null;
					try {
						fs = new FileOutputStream(fpath);
						fs.write(multifile.getBytes());
						
						filenames.add(filename); //db에 입력될 파일명
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							fs.close();
						} catch (IOException e) {
							e.printStackTrace();						
						}
					}
				}
			}
		}
		
		//파일업로드 2개한다는 전제
		//파일명 (DTO)
		board.setFilesrc(filenames.get(0));
		board.setFilesrc2(filenames.get(1));
		try {
			//동기화
			BoardDao boardDao = sqlsession.getMapper(BoardDao.class);
			//
			boardDao.boardUpdate(board); //DB update
		} catch (Exception e) {
			e.printStackTrace();
		}
		//처리가 끝나면 상세 페이지로: redirect 글번호를 가지고 ....
		return "redirect:boardDetail.htm?seq=" + board.getSeq(); //서버에게 새 요청
	}
	
	//파일 다운로드 서비스 함수
	public void download(String p, String f, HttpServletRequest request, HttpServletResponse response)
		throws IOException{
			String fname = new String(f.getBytes("euc-kr"), "8859_1");
			response.setHeader("Content-Disposition", "attachment;filename=" + fname + ";");
			
			String fullpath = request.getServletContext().getRealPath("/board/" + p + "/" + f);
			System.out.println(fullpath);
			FileInputStream fin = new FileInputStream(fullpath);
			
			ServletOutputStream sout = response.getOutputStream();
			byte[] buf = new byte[1024]; //전체를 다 읽지 않고 1024byte씩 읽어서
			int size = 0;
			while((size = fin.read(buf, 0, buf.length)) != -1) {
				sout.write(buf, 0, size);
			}
			fin.close();
			sout.close();
		}
	
}
