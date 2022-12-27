package dao;

import java.util.List;

import dto.Board;


//CRUD
public interface BoardDao {
	
	//게시물 개수
	int getCount(String field, String query);
	
	//전체 게시물
	List<Board> getBoards(int page, String field, String query);
	
	//게시물 삭제
	int delete(String seq);
	
	//게시물 수정
	int update(Board board);
	
	//게시물 상세
	Board boardDetail(String seq);
	
	//게시물 입력
	int insert(Board n);

}
