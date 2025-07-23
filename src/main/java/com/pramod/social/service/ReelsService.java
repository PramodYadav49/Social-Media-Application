package com.pramod.social.service;

import java.util.List;

import com.pramod.social.models.Reels;
import com.pramod.social.models.User;

public interface ReelsService {
	public Reels CreateReels(Reels reel, User user);
	public List<Reels> findAllReels();
	public List<Reels>findUserReel(Integer userId) throws Exception;

}
