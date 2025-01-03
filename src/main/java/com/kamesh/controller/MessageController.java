package com.kamesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kamesh.models.Message;
import com.kamesh.models.User;
import com.kamesh.service.MessageService;
import com.kamesh.service.UserService;

@RestController
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/message/createMessage/chat/{chatId}")
	public Message createMessage(@RequestHeader("Authorization") String jwt,@PathVariable Integer chatId,@RequestBody Message message) throws Exception {
		
		User reqUser = userService.findUserByToken(jwt);
		
		Message createdMessage = messageService.createMessage(reqUser, chatId, message); 
		
		return createdMessage; 
	}
	
	@GetMapping("api/message/allMessages/{chatId}")
	public List<Message> findChatsMessages(@PathVariable Integer chatId) throws Exception{
		return messageService.findChatsMessages(chatId);
	}
}
