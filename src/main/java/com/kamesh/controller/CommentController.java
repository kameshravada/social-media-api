package com.kamesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.kamesh.models.Comment;
import com.kamesh.models.Post;
import com.kamesh.models.User;
import com.kamesh.service.CommentService;
import com.kamesh.service.PostService;
import com.kamesh.service.UserService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping("/api/comment/post/{postId}")
	public Comment createComment(@PathVariable Integer postId,@RequestBody Comment comment,@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByToken(jwt);
		Post post = postService.findPostById(postId);
		
		Comment createdComment = commentService.createComment(comment, post.getId(), user.getId());
	
		return createdComment;
	}
	
	@PutMapping("/api/comment/like/{commentId}")
	public Comment likeComment(@PathVariable Integer commentId,@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByToken(jwt);
		
		Comment likedComment = commentService.likeComment(commentId, user.getId());
		
		return likedComment;
	}
}
