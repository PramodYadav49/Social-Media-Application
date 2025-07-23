package com.pramod.social.service;

import java.util.List;

import com.pramod.social.models.Chat;
import com.pramod.social.models.Message;
import com.pramod.social.models.User;

public interface MessageService {
	public Message Createmessage(User user,Integer ChatId,Message req) throws Exception;
	public List<Message>findChatsMessages(Integer ChatId) throws Exception;
	
}
