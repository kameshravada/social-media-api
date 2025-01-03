package com.kamesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamesh.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
	
	public List<Message> findByChatId(Integer chatId);
}
