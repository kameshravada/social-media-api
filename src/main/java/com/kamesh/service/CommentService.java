package com.kamesh.service;

import org.springframework.stereotype.Service;

import com.kamesh.models.Comment;

@Service
public interface CommentService {
	
	public Comment createComment(Comment comment, Integer postId,Integer userId) throws Exception;
	
	public Comment findCommentById(Integer commentId) throws Exception;
	
	public Comment likeComment(Integer commentId, Integer userId) throws Exception;
	
}
