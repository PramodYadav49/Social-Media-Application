package com.pramod.social.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String caption;
	private String imageurl;
	private String video;
	@JsonIgnore
	@ManyToOne
	
	private User user;
	private LocalDateTime createAt;
	@OneToMany
	private List<Comment> comments=new ArrayList<>();
	@JsonIgnore
	@OneToMany
	
	private List<User> liked=new ArrayList<>();
	public Integer getId() {
		return id;
	}
	public Post(Integer id, String caption, String imageurl, String video, User user, LocalDateTime createAt,
			List<User> liked) {
		super();
		this.id = id;
		this.caption = caption;
		this.imageurl = imageurl;
		this.video = video;
		this.user = user;
		this.createAt = createAt;
		this.liked = liked;
	}
	public List<User> getLiked() {
		return liked;
	}
	public void setLiked(List<User> liked) {
		this.liked = liked;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public Post(Integer id, String caption, String imageurl, String video, User user, LocalDateTime createAt) {
		super();
		this.id = id;
		this.caption = caption;
		this.imageurl = imageurl;
		this.video = video;
		this.user = user;
		this.createAt = createAt;
	}
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public Post(Integer id, String caption, String imageurl, String video, User user, LocalDateTime createAt,
			List<Comment> comments, List<User> liked) {
		super();
		this.id = id;
		this.caption = caption;
		this.imageurl = imageurl;
		this.video = video;
		this.user = user;
		this.createAt = createAt;
		this.comments = comments;
		this.liked = liked;
	}
	
	
	
}
