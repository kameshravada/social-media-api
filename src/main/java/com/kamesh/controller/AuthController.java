package com.kamesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamesh.config.JwtProvider;
import com.kamesh.models.User;
import com.kamesh.repository.UserRepository;
import com.kamesh.request.LoginRequest;
import com.kamesh.response.AuthResponse;
import com.kamesh.service.CustomUserDetailsService;
//import com.kamesh.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
//	@Autowired
//	private UserService userService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/signup")
	public AuthResponse signUp(@RequestBody User user) throws Exception {
		
		User isExistUser = userRepo.findByEmail(user.getEmail());
		if (isExistUser!=null) {
			throw new Exception("This email is already used with another account");
		}
		
		
		User user1 = new User();
	 
		user1.setFirstName(user.getFirstName());
		user1.setLastName(user.getLastName());
		user1.setEmail(user.getEmail());
		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		user1.setGender(user.getGender());
		
		User savedUser=userRepo.save(user1);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse(token,"Registration success");
		
		return authResponse;
	}
	
	@PostMapping("/signin")
	public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassWord());

		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse(token,"Login Success");
		
		return authResponse;
	}


	private Authentication authenticate(String email, String passWord) {
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
		
		if(userDetails==null)
			throw new BadCredentialsException("invalid Username");
		
		if(!passwordEncoder.matches(passWord, userDetails.getPassword())) {
			throw new BadCredentialsException("Password is invalid");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities() );
	}
}
