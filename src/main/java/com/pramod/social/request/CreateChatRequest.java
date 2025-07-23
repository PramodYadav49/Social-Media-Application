package com.pramod.social.request;

import com.pramod.social.models.User;

public class CreateChatRequest {

private Integer userId;

public CreateChatRequest() {
	super();
	// TODO Auto-generated constructor stub
}

public Integer getUserId() {
	return userId;
}

public void setUserId(Integer userId) {
	this.userId = userId;
}

public CreateChatRequest(Integer userId) {
	super();
	this.userId = userId;
}

}
