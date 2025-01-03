package com.kamesh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kamesh.models.Reels;
import com.kamesh.models.User;

@Service
public interface ReelsService { 
	
	public Reels createReel(Reels reels,User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUsersReels(Integer userId) throws Exception;

}
