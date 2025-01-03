package com.kamesh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kamesh.models.Chat;
import com.kamesh.models.User;

@Service
public interface ChatService {
	
	public Chat createChat(User reqUser,User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
}
