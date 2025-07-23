package com.pramod.social.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String content;
	private String image;
	private LocalDateTime timeStamp;
	
	@ManyToOne
	
	private User user;
	@ManyToOne
	@JsonIgnore
	private Chat chat;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Chat getChat() {
		return chat;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(Integer id, String content, String image, LocalDateTime timeStamp, User user, Chat chat) {
		super();
		Id = id;
		this.content = content;
		this.image = image;
		this.timeStamp = timeStamp;
		this.user = user;
		this.chat = chat;
	}

	
	
	
}
