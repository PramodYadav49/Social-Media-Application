package com.pramod.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pramod.social.models.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
	
	public List<Message> findChatById(Integer chatId);

}
