package com.kamesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kamesh.models.Reels;
import com.kamesh.models.User;
import com.kamesh.service.ReelsService;
import com.kamesh.service.UserService;

@RestController
public class ReelsController {
	
	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels/createReel")
	public Reels createReel(@RequestBody Reels reel,@RequestHeader("Authorization") String jwt ) {
		User reqUser = userService.findUserByToken(jwt);
		
		Reels createdReel = reelsService.createReel(reel, reqUser);
		
		return createdReel;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		List<Reels> reels = reelsService.findAllReels();
		return reels;
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
		List<Reels> reels = reelsService.findUsersReels(userId);
		return reels;
	}
}
