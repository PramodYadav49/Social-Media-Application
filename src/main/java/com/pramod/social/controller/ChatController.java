package com.pramod.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.social.models.Chat;
import com.pramod.social.models.User;
import com.pramod.social.repository.ChatRepository;
import com.pramod.social.request.CreateChatRequest;
import com.pramod.social.service.ChatService;
import com.pramod.social.service.UserService;

@RestController
public class ChatController {
	@Autowired
	private ChatService chatService;
	@Autowired
	private UserService userService;
	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest chat) throws Exception {
		User requser=userService.findUserByJwt(jwt);
		User user2=userService.findUserById(chat.getUserId());
		Chat chat2=chatService.CreateChat(requser,user2);
		return chat2;
	}
	@GetMapping("/api/chats")
	public List<Chat> findChat(@RequestHeader("Authorization") String jwt) {
		User user=userService.findUserByJwt(jwt);
		return chatService.findUsersChat(user.getId());
	}

}
