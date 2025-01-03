package com.kamesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kamesh.models.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Integer> {
	
	
	public List<Story> findByuserId(Integer userId);
}
