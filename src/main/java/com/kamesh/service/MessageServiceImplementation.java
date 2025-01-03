package com.kamesh.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamesh.models.Chat;
import com.kamesh.models.Message;
import com.kamesh.models.User;
import com.kamesh.repository.ChatRepository;
import com.kamesh.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService {
	
	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRepository chatRepo;

	@Override
	public Message createMessage(User user, Integer chatId, Message message) throws Exception {
		
		Chat chat = chatService.findChatById(chatId);
		
		Message newMessage = new Message();
		
		newMessage.setChat(chat);
		newMessage.setContent(message.getContent());
		newMessage.setImage(message.getImage());
		newMessage.setTimeStamp(LocalDateTime.now());
		newMessage.setUser(user);
		
			
		Message savedMessage = messageRepo.save(newMessage);
		chatRepo.save(chat);
		chat.getMessages().add(newMessage);
		
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
		
		Chat chat = chatService.findChatById(chatId);
		
		return messageRepo.findByChatId(chat.getId());
	}

}
