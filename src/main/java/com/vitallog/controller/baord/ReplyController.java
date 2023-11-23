package com.vitallog.controller.baord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vitallog.domain.board.Reply;
import com.vitallog.service.board.ReplyService;

@RestController
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("/api/vitallog/community/{postid}/comments")
	public List<Reply> getReply(@PathVariable int postid){
		return replyService.getReply(postid);
	}
	
	@PostMapping("/api/vitallog/community/{postid}/comments")
	public ResponseEntity<?> createReply(@PathVariable int postid, @RequestBody Reply reply){
		return replyService.createReply(postid,reply);
	}
	
	@DeleteMapping("/api/vitallog/community/{userid}/comments/{replyid}")
	public ResponseEntity<?> deleteReply(@PathVariable String userid, @PathVariable int replyid) {
		return replyService.deleteReply(userid, replyid);
	}
	
	@PostMapping("/api/vitallogi/community/{userid}/comments/{replyid}")
	public ResponseEntity<?> updateReply(@PathVariable String userid, @PathVariable int replyid, String contents) {
		return replyService.updateReply(userid, replyid, contents);
	}
}
