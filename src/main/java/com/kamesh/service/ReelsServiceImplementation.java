package com.kamesh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.kamesh.models.Reels;
import com.kamesh.models.User;
import com.kamesh.repository.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelsService {
	
	@Autowired
	public ReelsRepository reelsRepo;
	
	@Autowired
	private UserService userService;
	
	
	
	@Override
	public Reels createReel(Reels reels, User user) {

		Reels newReel = new Reels();
		newReel.setTitle(reels.getTitle());
		newReel.setVideo(reels.getVideo());
		newReel.setUser(user); 
		
		return reelsRepo.save(newReel);
	}

	@Override
	public List<Reels> findAllReels() {
		// TODO Auto-generated method stub
		return reelsRepo.findAll();
	}

	@Override
	public List<Reels> findUsersReels(Integer userId) throws Exception {
		// TODO Auto-generated method stub
		userService.findUserById(userId);
		return reelsRepo.findByUserId(userId);
	}

}
