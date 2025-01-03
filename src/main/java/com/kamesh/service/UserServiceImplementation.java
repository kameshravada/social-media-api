package com.kamesh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamesh.config.JwtProvider;
import com.kamesh.exceptions.UserException;
import com.kamesh.models.User;
import com.kamesh.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepo;
	
	

	@Override
	public User registerUser(User user) {
		User user1 = new User();
		user1.setId(user.getId());
		user1.setFirstName(user.getFirstName());
		user1.setLastName(user.getLastName());
		user1.setEmail(user.getEmail());
		user1.setPassword(user.getPassword());
		user1.setGender(user.getGender());
		
		User savedUser=userRepo.save(user1);

		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent())
			return user.get();
		
		throw new UserException("User not exist with provided user id "+userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user=userRepo.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws UserException {
		User reqUser=findUserById(reqUserId);
		User user2=findUserById(userId2);
		
//		reqUser.getFollowings().add(user2.getId());
//		user2.getFollowers().add(reqUser.getId());
		
		if(reqUser.getFollowings().contains(user2.getId())) {
			reqUser.getFollowings().remove(user2.getId());
			user2.getFollowers().remove(reqUser.getId());
		}else {
			reqUser.getFollowings().add(user2.getId());
			user2.getFollowers().add(reqUser.getId());
		}
		
		userRepo.save(reqUser);
		userRepo.save(user2);
		return reqUser;
	}

	@Override
	public User updateUser(User user,Integer id) throws UserException {
		Optional<User> user1 = userRepo.findById(id);
		
		if(user1.isEmpty())
			throw new UserException("User not exist with provided user id "+id);
		
		User oldUser = user1.get();
		
		if(user.getId()!=null)
			oldUser.setId(user.getId());
		if(user.getFirstName()!=null)
			oldUser.setFirstName(user.getFirstName());
		if(user.getLastName()!=null)
			oldUser.setLastName(user.getLastName());
		if(user.getEmail()!=null)
			oldUser.setEmail(user.getEmail());
		if(user.getPassword()!=null)
			oldUser.setPassword(user.getPassword());
		if(user.getGender()!=null)
			oldUser.setGender(user.getGender());
			
		return userRepo.save(oldUser);
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepo.searchUser(query);
	}

	@Override
	public User findUserByToken(String jwt) {
		String email = JwtProvider.getEmailFromJwt(jwt);
		
		User user = userRepo.findByEmail(email);
		
		return user;
	}

}
