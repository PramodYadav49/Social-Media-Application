package com.pramod.social.service;

import java.util.List;

import com.pramod.social.models.Chat;
import com.pramod.social.models.User;

public interface ChatService {
 public Chat CreateChat(User requser, User user);
 public Chat findChatById(Integer chatId) throws Exception;
 public List<Chat> findUsersChat(Integer userId);
}
