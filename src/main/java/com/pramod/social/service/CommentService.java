package com.pramod.social.service;

import com.pramod.social.models.Comment;

public interface CommentService {
	public Comment createComment(
			Comment comment
			,Integer UserId
			,Integer PostId) throws Exception;
	public Comment likeCommnet(Integer CommentId, Integer userId) throws Exception;
	public Comment FindCommentById(Integer CommentId) throws Exception;
		
	
}
