package com.kamesh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kamesh.models.Message;
import com.kamesh.models.User;

@Service
public interface MessageService {
	
	public Message createMessage(User user,Integer chatId,Message message) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
