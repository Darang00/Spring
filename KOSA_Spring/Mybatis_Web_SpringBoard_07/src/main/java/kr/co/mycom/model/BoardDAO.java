package kr.co.mycom.model;

import java.util.HashMap;
import java.util.List;

public interface BoardDAO {
//board CRUD
	/*
	 전체조회: 타입> List<Board>
	 조건조회: 타입>  Board
	 삽입
	 수정
	 삭제
	 필요에 따라서
	 조건검색
	 조회수 증가..
	 */
 //메서드 정의
 //CRUD 기반
 int insertBoard(BoardDTO dto);
 int updateBoard(BoardDTO dto);
 int deleteBoard(BoardDTO dto); //delete는 굳이  DTO 쓸 필요는 없음 >> int deleteBoard(String boardid)
 
 List<BoardDTO> getBoardList(HashMap map); //전체조회
 BoardDTO getBoard(int num); //조건조회
 
 void updateHit(int num); //조회수 증가 //추가 조회수 증가
 int getBoardCount(); //전체 글 수
 
 void insertReply(BoardDTO dto); //답변처리
 void updateSort(BoardDTO dto); //답변 처리(step(sort) 값 변경)
 
 void createTable(BoardDTO dto);
 
 
 void insertUserDto(HashMap map);
}
