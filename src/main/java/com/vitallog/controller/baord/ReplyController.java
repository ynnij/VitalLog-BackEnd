package com.vitallog.controller.baord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.vitallog.service.board.ReplyService;

@RestController
public class ReplyController {
	@Autowired
	private ReplyService replyService;
}
