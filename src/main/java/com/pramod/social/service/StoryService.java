package com.pramod.social.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pramod.social.models.Story;
import com.pramod.social.models.User;

public interface StoryService {
	public Story createStory(Story story, User userId);
	public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
