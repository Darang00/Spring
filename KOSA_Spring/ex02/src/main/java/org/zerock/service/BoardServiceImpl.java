package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	//spring 4.3 이상에서 작동 처리
	//private BoardMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	//등록 작업의 구현과 테스트
	@Override
	public void register(BoardVO board) {
		log.info("register........" + board);
		
		mapper.insertSelectKey(board);
	}

	//조회 작업의 구현과 테스트
	@Override
	public BoardVO get(Long bno) {
		log.info("get........................" + bno);
		return mapper.read(bno);
	}
	
	//수정
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify.................." + board);
		return mapper.update(board) == 1;
	}

	//삭제
	@Override
	public boolean remove(Long bno) {
		log.info("remove..." + bno);
		return mapper.delete(bno) == 1;
	}
	
	//목록(리스트) 작업의 구현과 테스트
	@Override
	public List<BoardVO> getList() {
		log.info("getList.................");
		return mapper.getList();
	}
	
	
}
