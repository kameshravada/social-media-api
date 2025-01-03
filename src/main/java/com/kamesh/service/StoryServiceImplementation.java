package com.kamesh.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamesh.models.Story;
import com.kamesh.models.User;
import com.kamesh.repository.StoryRepository;

@Service
public class StoryServiceImplementation implements StoryService{
	
	@Autowired
	private StoryRepository storyRepo;
	
	@Autowired
	private UserService userService;

	@Override
	public Story createStory(Story story, User user) {
		Story newStory = new Story();
		newStory.setCaption(story.getCaption());
		newStory.setImage(story.getImage());
		newStory.setUser(user);
		newStory.setTimeStamp(LocalDateTime.now());
		
		return storyRepo.save(newStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		User user = userService.findUserById(userId); 
		return storyRepo.findByuserId(user.getId());
	}

}
