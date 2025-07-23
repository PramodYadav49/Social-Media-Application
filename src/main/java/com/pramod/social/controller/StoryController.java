package com.pramod.social.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.social.models.Story;
import com.pramod.social.service.StoryService;
import com.pramod.social.service.UserService;

@RestController
public class StoryController {
	@Autowired
	private StoryService storyService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story,@RequestHeader("Authorization") String jwt) {
		
		Story createStory=storyService.createStory(story, userService.findUserByJwt(jwt));
		return createStory;
	}
	@GetMapping("/api/story/user/{userId}")
	public List<Story> findStoryByUser(@PathVariable Integer userId) throws Exception {
		
	
		return storyService.findStoryByUserId(userId) ;
	}
	

}
