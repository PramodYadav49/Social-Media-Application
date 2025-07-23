package com.pramod.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.social.models.Chat;
import com.pramod.social.models.User;
import com.pramod.social.repository.ChatRepository;
@Service
public class ChatServiceImplementation implements ChatService{
	@Autowired
	private ChatRepository chatRepository;
	

	@Override
	public Chat CreateChat(User requser, User user2) {
		Chat isExist=chatRepository.findChatByUsersId(user2, requser);
		if(isExist!=null) {
			return isExist;
		}
		Chat chat =new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(requser);
		chat.setTimestamp(LocalDateTime.now());
		
		
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		 Optional<Chat> opt=chatRepository.findById(chatId);
		 if(opt.isEmpty()) {
			 throw new Exception("chat not found with id - "+chatId);
		 }
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		
		return chatRepository.findByUsersId(userId);
	}

}
