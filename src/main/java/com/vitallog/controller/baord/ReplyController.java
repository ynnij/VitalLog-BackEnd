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

import com.vitallog.domain.board.Reply;
import com.vitallog.service.board.ReplyService;

@RequestMapping("/api/vitallog/community")
@RestController
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("/{postid}/comments")
	public List<Reply> getReply(@PathVariable int postid){
		return replyService.getReply(postid);
	}
	
	@PostMapping("/{postid}/comments")
	public ResponseEntity<?> createReply(@PathVariable int postid, @RequestBody Reply reply){
		return replyService.createReply(postid,reply);
	}
	
	@DeleteMapping("/{userid}/comments/{replyid}")
	public ResponseEntity<?> deleteReply(@PathVariable String userid, @PathVariable int replyid) {
		return replyService.deleteReply(userid, replyid);
	}
	
	@PutMapping("/{userid}/comments/{replyid}")
	public ResponseEntity<?> updateReply(@PathVariable String userid, @PathVariable int replyid, String contents) {
		return replyService.updateReply(userid, replyid, contents);
	}
}
