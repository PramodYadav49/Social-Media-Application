package com.pramod.social.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.social.models.Chat;
import com.pramod.social.models.Message;
import com.pramod.social.models.User;
import com.pramod.social.repository.ChatRepository;
import com.pramod.social.repository.MessageRepository;
@Service
public class MessageServiceImplementation implements MessageService{
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private ChatService chatService;
	@Autowired
	private ChatRepository chatRepository;
	@Override
	public Message Createmessage(User user, Integer ChatId, Message req) throws Exception {
		Chat chat =chatService.findChatById(ChatId);
		Message message=new Message();
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setTimeStamp(LocalDateTime.now());
		Message savedMessage=messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer ChatId) throws Exception {
		Chat chat =chatService.findChatById(ChatId);
		
		return messageRepository.findChatById(ChatId);
	}

}
