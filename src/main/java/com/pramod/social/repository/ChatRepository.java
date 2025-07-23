package com.pramod.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pramod.social.models.Chat;
import com.pramod.social.models.User;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
	
	public List<Chat> findByUsersId(Integer UserId);
	@Query("select c from Chat c Where :user Member of c.users And :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param ("user") User user, @Param("reqUser") User user2);
	
	
	

}
