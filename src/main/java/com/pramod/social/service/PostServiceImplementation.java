package com.pramod.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramod.social.models.Post;
import com.pramod.social.models.User;
import com.pramod.social.repository.PostRepository;
import com.pramod.social.repository.UserRepository;
@Service
public class PostServiceImplementation implements PostService{
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	

	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		Post newPost= new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImageurl(post.getImageurl());
		newPost.setCreateAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		User user=userService.findUserById(userId);
		newPost.setUser(user);
		postRepository.save(newPost);
		
		return null;
	}

	@Override
	public String deletePost(Integer PostId, Integer userId) throws Exception {
		Post post=findPostById(PostId);
		User user=userService.findUserById(userId);
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("You can't delete anothers  users post");
		}
		postRepository.delete(post);
		return "Post deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws Exception {
		return postRepository.findPostByUserId(userId);
		
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> opt=postRepository.findById(postId);
		if(opt.isEmpty()) {
			throw new Exception("Post is not found with id "+postId);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {
		
		return postRepository.findAll();
	}

	@Override
	public Post savePost(Integer postId, Integer userId) throws Exception {
		Post post=findPostById(postId);
        User user=userService.findUserById(userId);
        if(user.getSavedPosts().contains(post)) {
        	user.getSavedPosts().remove(post);
        }
        else {
        	user.getSavedPosts().add(post);
        }
   userRepository.save(user);
   return post;
        
		
	}

	@Override
	public Post likePost(Integer postId,Integer userId) throws Exception {
		Post post=findPostById(postId);
        User user=userService.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);
		}
		
		return postRepository.save(post);
		
	}

}
