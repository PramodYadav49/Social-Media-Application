package com.pramod.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.social.models.Post;
import com.pramod.social.models.User;
import com.pramod.social.response.ApiResponse;
import com.pramod.social.service.PostService;
import com.pramod.social.service.UserService;

@RestController
public class PostController {
	@Autowired
	UserService userService;
	@Autowired
	PostService postService;
	@PostMapping("/api/posts/users")
	public ResponseEntity<Post> createPost(@RequestBody Post post,@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUserByJwt(jwt);
		
		
		Post createdPost=postService.createNewPost(post, user.getId());
		return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
		
	}
	@DeleteMapping("/api/posts/{postId}/user")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUserByJwt(jwt);
		String message=postService.deletePost(postId, user.getId());
		ApiResponse res=new ApiResponse(message,true);
		
		
		
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception{
		Post newPost=postService.findPostById(postId);
		
		
		return new ResponseEntity<Post>(newPost,HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/api/posts/user")
	public ResponseEntity<List<Post>> findUserPost(@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUserByJwt(jwt);
		List<Post> postList=postService.findPostByUserId(user.getId());
		return new ResponseEntity<List<Post>>(postList,HttpStatus.OK);
	}
	
	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost() throws Exception{
		List<Post> postList=postService.findAllPost();
		return new ResponseEntity<List<Post>>(postList,HttpStatus.OK);
	}
	
	@PutMapping("/posts/user/{postId}")
	public ResponseEntity<Post> savePostHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception{
		User user=userService.findUserByJwt(jwt);
		Post postList=postService.savePost(postId, user.getId());
		return new ResponseEntity<Post>(postList,HttpStatus.OK);
	}
	
	@PutMapping("/api/posts/like/user/{postId}")
	public ResponseEntity<Post> postLikedHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception{
		User user=userService.findUserByJwt(jwt);
		Post postList=postService.likePost(postId, user.getId());
		return new ResponseEntity<Post>(postList,HttpStatus.OK);
	}

	
	
	
	
	
}
