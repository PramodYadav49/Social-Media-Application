package com.pramod.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramod.social.models.Story;

public interface StoryRepository extends JpaRepository<Story, Integer>{
	public List<Story> findByUserId(Integer userId);

}
