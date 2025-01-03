package com.kamesh.service;

import java.util.List;

import com.kamesh.models.Post;

public interface PostService {
Post createNewPost(Post post,Integer userId) throws Exception;
	
	String deletePost(Integer postId,Integer userId) throws Exception;
	
	List<Post> findPostByUserId(Integer userId) throws Exception;
	
	Post findPostById(Integer postId) throws Exception;
	
	List<Post> findAllpost();
	
	Post savedPost(Integer postId,Integer userId) throws Exception;
	
	Post likePost(Integer postId,Integer userId) throws Exception;
}
