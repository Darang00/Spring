package controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dto.Board;
import service.BoardService;

@Controller
@RequestMapping("/board/")
public class BoardController {
	private BoardService boardService;
	
	@Autowired
	public void setBoardservice(BoardService boardService) {
		this.boardService = boardService;
	}
	
	//글 목록
	@RequestMapping("boardList.htm") //  /board/board.htm
	public String boardList(String pg, String f, String q, Model model) {
		List<Board> boardList = boardService.boardList(pg, f, q);
		model.addAttribute("boardList", boardList); //자동으로 board.jsp forward
		return "board/boardList";
	}
	
	//글 상세보기
	@RequestMapping("boardDetail.htm")
	public String boardDetail(String seq, Model model) {
		Board board = boardService.boardDetail(seq);
		model.addAttribute("board", board);
		return "board/boardDetail";
	}
	
	//글쓰기
	@GetMapping(value="boardWrite.htm") //view 쪽에서 href에 boardWrite.htm이거 있어서 이 함수를 탄다. 
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	@PostMapping(value="boardWrite.htm")
	public String boardWrite(Board board, HttpServletRequest request, Principal principal) {
		String url = null;
		try {
			url = boardService.boardWrite(board, request, principal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
	
	@GetMapping(value="boardUpdate.htm")
	public String boardEdit(String seq, Model model) {
		Board board = null;
		try {
			board = boardService.boardUpdate(seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("board", board);
		return "board/boardUpdate";
	}
	
	@PostMapping(value="boardUpdate.htm")
	public String boardUpdate(Board board, HttpServletRequest request) {
		return boardService.boardUpdate(board, request);
	}
	
	@RequestMapping("boardDelete.htm")
	public String boardDelete(String seq) {
		return boardService.boardDelete(seq);
	}
	
	//파일 다운로드
	@RequestMapping("download.htm")
	public void download(String p, String f, HttpServletRequest request, HttpServletResponse response) throws IOException{
		boardService.download(p, f, request, response);
	} 
}
