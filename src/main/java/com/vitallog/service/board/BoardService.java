package com.vitallog.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.vitallog.domain.board.Board;
import com.vitallog.persistence.board.BoardRepository;
import com.vitallog.persistence.board.ReplyRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private ReplyRepository replyRepo;
	
	public List<Board> getBoardList(){
		return boardRepo.findAll();
	}
	
	@Transactional
	public ResponseEntity<?> createBoard(Board board){
		try{
			boardRepo.save(board);
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(null);
	}
	
	@Transactional
	public Board getBoardDetail(int id) {
		Board board = boardRepo.findById(id).get();
		//상세글 조회 시 조회수 1 증가 
		board.setVisitcount(board.getVisitcount()+1);
		boardRepo.save(board);
		
		return board;
	}
	
	@Transactional
	public ResponseEntity<?> updateBoard(int id, Board board){
		Board findBoard = boardRepo.findById(id).get();
		findBoard.setTitle(board.getTitle());
		findBoard.setContents(board.getContents());
		boardRepo.save(findBoard);
		return ResponseEntity.ok().build();
	}
	
	@Transactional
	public ResponseEntity<?> deleteBoard(int id){
		boardRepo.deleteById(id);
		return ResponseEntity.ok().build(); 
	}
	
	@Transactional
	public ResponseEntity<?> deleteBoardAndReply(@PathVariable Integer postid){
		replyRepo.deleteReply(postid);
		boardRepo.deleteById(postid);
		return ResponseEntity.ok().build();
	}
}
