package com.pramod.social.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.social.models.User;
import com.pramod.social.repository.UserRepository;
import com.pramod.social.service.UserService;

import jakarta.websocket.server.PathParam;

@RestController()
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	

	@GetMapping("/api/users")
	public List<User> getUser() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@GetMapping("/api/users/{userid}")
	public User getUserById(@PathVariable("userid") Integer userid) throws Exception {
		User user=userService.findUserById(userid);
		return user;

	}

	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
		User user2=userService.findUserByJwt(jwt);
		int userid=user2.getId();
		User updateUser=userService.updateUser(user, userid);
		return updateUser;
		
	}
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer userId2) throws Exception {
		User user2=userService.findUserByJwt(jwt);
		int userid=user2.getId();
		User user=userService.followUser(userid, userId2);
		return user;
	}
	@GetMapping("/api/users/search")
	public List<User> SearchUser(@Param("query")String Query){
		List<User> users=userService.serachUser(Query);
		return users;
	}
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		User user=userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}
}
