package com.kamesh.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kamesh.exceptions.UserException;
import com.kamesh.models.User;
import com.kamesh.repository.UserRepository;
import com.kamesh.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	
//	@PostMapping("/addUser")
//	public User addUser(@RequestBody User user) {
//		User savedUser = userService.registerUser(user);
//		return savedUser;
//	}
	
	@GetMapping("/api/getUser")
	public User getUser() {
		User user1 = new User();
		return user1;
	}
	
	@GetMapping("/api/users")
	public List<User> getAllUsers() {
		
		return userRepo.findAll();
	}
	
	@GetMapping("/api/get/{id}")
	public User getUserById(@PathVariable Integer id) throws UserException {
		
		User user=userService.findUserById(id);
		return user;
	}
	
	
	
	@PutMapping("/api/updateUser")
	public User updateUser(@RequestHeader("Authorization") String jwt,@RequestBody User user) throws UserException{
		User reqUser = userService.findUserByToken(jwt);
		User user1=userService.updateUser(user, reqUser.getId());
		return user1;
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer userId2) throws UserException {
		User reqUser = userService.findUserByToken(jwt);
		User user=userService.followUser(reqUser.getId(), userId2);
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam String query){
		List<User> users = userService.searchUser(query);
		return users;
	}
	@DeleteMapping("/api/users/delete")
	public String deleteById(@RequestHeader("Authorization") String jwt) throws UserException {
//		Optional<User> user1 = userRepo.findById(id);
//		
//		if(user1.isEmpty())
//			throw new Exception("User not exist with provided user id "+id);
//		else
//			userRepo.delete(user1.get());
//		
//		return "User with id "+id+" is deleted successfully";
		User user =userService.findUserByToken(jwt);
		userRepo.deleteById(user.getId());
		return "Your account is successfully deleted";
	}
	
	
	@GetMapping("/api/users/profile")
	public User findUSerByToken(@RequestHeader("Authorization") String jwt) {
		
		User user = userService.findUserByToken(jwt);
		user.setPassword(null);
		
		return user;
	}
}
