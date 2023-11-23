package com.vitallog.controller.baord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitallog.domain.board.Board;
import com.vitallog.persistence.board.ReplyRepository;
import com.vitallog.service.board.BoardService;

@RequestMapping("/api/vitallog/community")
@RestController
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	
	// 전체 게시판 데이터 리턴
	@GetMapping("/board") 
	public List<Board> getBoardList(){
		return boardService.getBoardList();
	}
	
	// 게시글 작성 
	@PostMapping("/write")
	public ResponseEntity<?> createBoard(@RequestBody Board board){
		return boardService.createBoard(board);
	}
	
	// 게시글 상세보기
	@GetMapping("/detail/{postid}")
	public Board getBoardDetail(@PathVariable Integer postid) {
		return boardService.getBoardDetail(postid);
	}
	
	//게시글 수정
	@PutMapping("/board/{postid}")
	public ResponseEntity<?> updateBoard(@PathVariable Integer postid, @RequestBody Board board){
		return boardService.updateBoard(postid, board);
	}
	
	//게시글 삭제
	@DeleteMapping("/board/{postid}")
	public ResponseEntity<?> deleteBoardAndReply(@PathVariable Integer postid){
		return boardService.deleteBoardAndReply(postid);
	}
	
}
