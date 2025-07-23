package com.pramod.social.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.social.models.Comment;
import com.pramod.social.models.Post;
import com.pramod.social.models.User;
import com.pramod.social.repository.CommentRepository;
import com.pramod.social.repository.PostRepository;
@Service
public class CommentServiceImplementation implements CommentService{
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentRepository commentRepository;
	
	
	@Autowired
	private PostRepository postRepository;
	

	@Override
	public Comment createComment(Comment comment, Integer UserId, Integer PostId) throws Exception {
		User user=userService.findUserById(UserId);
		Post post=postService.findPostById(PostId);
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		Comment savedComment=commentRepository.save(comment);
		post.getComments().add(savedComment);	
		postRepository.save(post);
		return savedComment;
	}

	@Override
	public Comment likeCommnet(Integer CommentId, Integer userId) throws Exception {
		Comment comment=FindCommentById(CommentId);
		User user=userService.findUserById(userId);
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}
		else comment.getLiked().remove(user);
		return commentRepository.save(comment);
	}

	@Override
	public Comment FindCommentById(Integer CommentId) throws Exception {
		Optional<Comment> opt=commentRepository.findById(CommentId);
		if(opt.isEmpty()) {
			throw new Exception("Comment not exist");
		}
		return opt.get();
	}

}
