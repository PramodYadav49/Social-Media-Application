package com.pramod.social.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.social.models.Story;
import com.pramod.social.models.User;
import com.pramod.social.repository.StoryRepository;
@Service
public class StoryServiceImplementation implements StoryService{
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;
	
		
		
		
		
		@Override
	public Story createStory(Story story, User user) {
			Story createStory=new Story();
			createStory.setCaption(story.getCaption());
			createStory.setId(story.getId());
			createStory.setImage(story.getImage());
			createStory.setTimestamp(LocalDateTime.now());
			createStory.setUser(user);
		return storyRepository.save(createStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		User user=userService.findUserById(userId);
		return storyRepository.findByUserId(userId);
	}

}
