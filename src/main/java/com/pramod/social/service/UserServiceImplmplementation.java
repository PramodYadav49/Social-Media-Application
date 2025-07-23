package com.pramod.social.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.social.config.JwtProvider;
import com.pramod.social.exception.UserException;
import com.pramod.social.models.User;
import com.pramod.social.repository.UserRepository;

@Service
public class UserServiceImplmplementation implements UserService{
	@Autowired
	UserRepository userRepository;
	
	

	@Override
	public User registerUser(User user) {
		 User newUser=new User(); 
		  newUser.setId(user.getId());
		  newUser.setFirstName(user.getFirstName());
		  newUser.setLastName(user.getLastName()); 
		  newUser.setEmail(user.getEmail());
		 newUser.setPassword(user.getPassword());
		 
		User saveUser=userRepository.save(newUser);
		return saveUser;
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}
		throw new UserException("user not exist With userId" + userId);
		
	}

	@Override
	public User findUserByEmail(String email) {
		User user=userRepository.findByEmail(email);
		
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws UserException {
		System.out.println("inside followUser");
		User reqUser=findUserById(reqUserId);
		User user2=findUserById(userId2);
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());
		userRepository.save(reqUser);
		userRepository.save(user2);
		return reqUser;
	}

	@Override
	public User updateUser(User user,Integer userId) throws UserException {
		Optional<User> optUser = userRepository.findById(userId);
		if (optUser.isEmpty()) {
			throw new UserException("User Not Found");
		}
		User user2 = optUser.get();

		if (user.getFirstName() != null) {
			user2.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			user2.setLastName(user.getLastName());
		}
		if (user.getEmail() != null) {
			user2.setEmail(user.getEmail());
		}
		if (user.getPassword() != null) {
			user2.setPassword(user.getPassword());
		}
		User updatedUser = userRepository.save(user2);
		return updatedUser;
	}

	@Override
	public List<User> serachUser(String query) {
		List<User> users=userRepository.searchUser(query);
		return users;
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		User user=userRepository.findByEmail(email);
		return user;
	}



	


	
	
}
