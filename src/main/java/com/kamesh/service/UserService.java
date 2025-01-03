package com.kamesh.service;

import java.util.List;

import com.kamesh.exceptions.UserException;
import com.kamesh.models.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws UserException;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userId1,Integer userId2) throws UserException;
	
	public User updateUser(User user,Integer id) throws UserException;
	
	public List<User> searchUser(String query);
	
	public User findUserByToken(String jwt);
	
	
}
