package com.pramod.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.social.models.Comment;
import com.pramod.social.models.User;
import com.pramod.social.service.CommentService;
import com.pramod.social.service.UserService;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@RestController


public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/comments/post/{postId}")
	
	public Comment createComment (@RequestBody Comment comment,@RequestHeader("Authorization") String jwt,@PathVariable("postId") Integer postId) throws Exception {
		User user=userService.findUserByJwt(jwt);
		Comment createdComment=commentService.createComment(comment, postId, user.getId());
		return createdComment;
		
	}
@PutMapping("/api/comments/like/{commentId}")
	
	public Comment likeComment (@RequestHeader("Authorization") String jwt,@PathVariable("commentId") Integer commentId) throws Exception {
		User user=userService.findUserByJwt(jwt);
		Comment likeComment=commentService.likeCommnet(commentId, user.getId());
		return likeComment;
		
	}
}
