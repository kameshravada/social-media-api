package com.kamesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kamesh.models.Story;
import com.kamesh.models.User;
import com.kamesh.service.StoryService;
import com.kamesh.service.UserService;

@RestController
public class StoryController {
	
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/story/createStory")
	public Story createStory(@RequestBody Story story,@RequestHeader("Authorization") String jwt){
		User reqUser = userService.findUserByToken(jwt);
		Story newStory = storyService.createStory(story, reqUser);
		return newStory;
	}
	
	
	@GetMapping("/api/story/user/{userId}")
	public List<Story> findUserStory(@PathVariable Integer userId) throws Exception{
		List<Story> stories = storyService.findStoryByUserId(userId);
		return stories;
	}
	
	
}
