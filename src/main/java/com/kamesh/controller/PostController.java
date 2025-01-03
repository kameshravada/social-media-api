package com.kamesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kamesh.models.Post;
import com.kamesh.models.User;
import com.kamesh.response.ApiResponse;
import com.kamesh.service.PostService;
import com.kamesh.service.UserService;

@RestController
public class PostController {
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("api/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String jwt) throws Exception{
		User requser = userService.findUserByToken(jwt);
		Post createdPost = postService.createNewPost(post, requser.getId());
		return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/posts/deletePost/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		User requser = userService.findUserByToken(jwt);
		String message = postService.deletePost(postId, requser.getId());
		ApiResponse response = new ApiResponse(message,true);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
	}
	
	@GetMapping("/api/posts/id/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception{
		Post post = postService.findPostById(postId);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/api/posts/userPosts/{userId}")
	public ResponseEntity<List<Post>> findUsersPostHandler(@PathVariable Integer userId) throws Exception{
		List<Post> post = postService.findPostByUserId(userId);
		return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPostHaandler() throws Exception{
		List<Post> post = postService.findAllpost();
		return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
	}
	
	@PutMapping("api/posts/save/{postId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		User reqUser = userService.findUserByToken(jwt);
		Post post = postService.savedPost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("api/posts/like/{postId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		User reqUser = userService.findUserByToken(jwt);
		Post post = postService.likePost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
}
