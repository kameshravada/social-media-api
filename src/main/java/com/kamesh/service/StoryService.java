package com.kamesh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kamesh.models.Story;
import com.kamesh.models.User;

@Service
public interface StoryService {
	
	public Story createStory(Story story,User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
	
}
