package com.pramod.social.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
	

	private String lastName;
	private String email;
	private String password;
	private List<Integer> followers=new ArrayList<>();
	private List<Integer> followings=new ArrayList<>();
	@JsonIgnore
	@ManyToMany
	
	private List<Post> savedPosts=new ArrayList<>();
	private String Gender;
	
	public User(int id, String firstName, String lastName,List<Post>savedPosts, String email, String password, List<Integer> followers,
			List<Integer> followings, String gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.followers = followers;
		this.savedPosts=savedPosts;
		this.followings = followings;
		
		Gender = gender;
	}


	public List<Post> getSavedPosts() {
		return savedPosts;
	}


	public void setSavedPosts(List<Post> savedPosts) {
		this.savedPosts = savedPosts;
	}


	public List<Integer> getFollowers() {
		return followers;
	}


	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}


	public List<Integer> getFollowings() {
		return followings;
	}


	public void setFollowings(List<Integer> followings) {
		this.followings = followings;
	}


	public String getGender() {
		return Gender;
	}


	public void setGender(String gender) {
		Gender = gender;
	}


	public User() {
		
	}


	public User(int id, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
