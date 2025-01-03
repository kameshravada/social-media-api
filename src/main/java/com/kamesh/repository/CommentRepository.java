package com.kamesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamesh.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	

}
