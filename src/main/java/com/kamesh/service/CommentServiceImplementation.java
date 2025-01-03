package com.kamesh.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kamesh.models.Comment;
import com.kamesh.models.Post;
import com.kamesh.models.User;
import com.kamesh.repository.CommentRepository;

@Service
public class CommentServiceImplementation implements CommentService{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		Comment newComment = new Comment();
		User user = userService.findUserById(userId);
		Post post = postService.findPostById(postId);
		newComment.setContent(comment.getContent());
		newComment.setUser(user);
		newComment.setCreatedAt(LocalDateTime.now());
		
		Comment savedComment = commentRepo.save(newComment);
		post.getComments().add(savedComment);
		
		return savedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		Optional<Comment> comment = commentRepo.findById(commentId);
		
		if(comment.isEmpty()) {
			throw new Exception("Comment not found");
		}
		return comment.get();
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		Comment comment = findCommentById(commentId);
		User user = userService.findUserById(userId);
		
		if(comment.getLiked().contains(user)) {
			comment.getLiked().remove(user);
		}else {
			comment.getLiked().add(user);
		}
		return commentRepo.save(comment);
	}

}
