package com.pramod.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.social.models.Reels;
import com.pramod.social.models.User;
import com.pramod.social.repository.ReelsRepository;
@Service
public class ReelsServiceImplementation implements ReelsService{
	@Autowired
    ReelsRepository reelsRepository;
	@Autowired
	private UserService userService;
	@Override
	public Reels CreateReels(Reels reel, User user) {
		Reels createReel=new Reels();
		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setVideo(reel.getVideo());
		
		return reelsRepository.save(createReel);
	}

	@Override 
	public List<Reels> findAllReels() {
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUserReel(Integer userId) throws Exception {
		userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
