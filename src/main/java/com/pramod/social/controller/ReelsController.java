package com.pramod.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.social.models.Reels;
import com.pramod.social.models.User;
import com.pramod.social.service.ReelsService;
import com.pramod.social.service.UserService;

@RestController 
public class ReelsController {
	@Autowired
	private ReelsService reelsService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels CreateReels(@RequestBody Reels reel,@RequestHeader("Authorization") String jwt) {
		User reqUser=userService.findUserByJwt(jwt);
		
		Reels createdReels=reelsService.CreateReels(reel, reqUser);
		return createdReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		
		return reelsService.findAllReels();
	}
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels>findReelsByUserid(@PathVariable Integer userId) throws Exception{
		
		
		return reelsService.findUserReel(userId);
		
		
	}

}
