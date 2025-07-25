package com.pramod.social.service;

import java.util.List;

import com.pramod.social.exception.UserException;
import com.pramod.social.models.User;

public interface UserService {
		public User registerUser(User user);
		public User findUserById(Integer userId) throws UserException;
		public User findUserByEmail(String email);
		public User followUser(Integer userId1,Integer UserId2) throws UserException;
	
		public User updateUser(User user, Integer userId) throws UserException;
		public List<User> serachUser(String query);
		public User findUserByJwt(String jwt);
}
