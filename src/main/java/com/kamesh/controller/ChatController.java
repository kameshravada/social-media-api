package com.kamesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kamesh.models.Chat;
import com.kamesh.models.User;
import com.kamesh.request.CreateChatRequest;
import com.kamesh.service.ChatService;
import com.kamesh.service.UserService;

@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/chats/createChat")
	public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest chatRequest) throws Exception{
		User reqUser = userService.findUserByToken(jwt);
		User user2 = userService.findUserById(chatRequest.getUserId());
		Chat chat = chatService.createChat(reqUser, user2);
		
		return chat;
	}
	
	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
		User user = userService.findUserByToken(jwt);
		List<Chat> chats = chatService.findUsersChat(user.getId());
		
		return chats;
	}
}
