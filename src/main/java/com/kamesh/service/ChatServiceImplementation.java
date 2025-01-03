package com.kamesh.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamesh.models.Chat;
import com.kamesh.models.User;
import com.kamesh.repository.ChatRepository;


@Service
public class ChatServiceImplementation implements ChatService{
	

	@Autowired
	private ChatRepository chatRepo;
	
	
	
	
	@Override
	public Chat createChat(User reqUser, User user2) {
		Chat isExist = chatRepo.findChatByUsersId(reqUser, user2);
		if(isExist!=null) {
			return isExist;
		}
		
		Chat newChat = new Chat();
//		newChat.setChat_image(null);
//		newChat.setChat_name(null);
		newChat.setTimeStamp(LocalDateTime.now());
		newChat.getUsers().add(user2);
		newChat.getUsers().add(reqUser);
		
		return chatRepo.save(newChat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		Optional<Chat> chat = chatRepo.findById(chatId);
		
		if(chat.isEmpty()) {
			throw new Exception("Chat not found with id "+chatId);
		}
		return chat.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		// TODO Auto-generated method stub
		return chatRepo.findByUsersId(userId);
	}

}
