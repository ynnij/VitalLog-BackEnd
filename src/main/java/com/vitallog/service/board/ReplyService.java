package com.vitallog.service.board;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vitallog.domain.board.Reply;
import com.vitallog.persistence.board.ReplyRepository;

import jakarta.transaction.Transactional;

@Service
public class ReplyService {
	@Autowired
	private ReplyRepository replyRepo;
	
	public List<Reply> getReply(int boardId) {
		return replyRepo.findByBoardId(boardId);
	}
	
	@Transactional
	public ResponseEntity<?> createReply(int postid, Reply reply) {
		try {
			reply.setBoardId(postid);
			reply.setUpdateDate(reply.getCreateDate());
			replyRepo.save(reply);
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	
		return ResponseEntity.ok().build();
	}
	
	@Transactional
	public ResponseEntity<?> deleteReply(String userid, int replyid) {
		Reply reply = replyRepo.findById(replyid).get();
		if(!reply.getWriter().equals(userid))
			return ResponseEntity.badRequest().build();
		replyRepo.deleteById(replyid);
		return ResponseEntity.ok().build();
	}
	
	@Transactional
	public ResponseEntity<?> updateReply(String userid, int replyid, String contents) {
		Reply reply = replyRepo.findById(replyid).get();
		if(!reply.getWriter().equals(userid))
			return ResponseEntity.badRequest().build();
		
		reply.setContents(contents);
		reply.setUpdateDate(new Date());
		replyRepo.save(reply);
		return ResponseEntity.ok().build();
	}
}
