package com.pramod.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pramod.social.config.JwtProvider;
import com.pramod.social.models.User;
import com.pramod.social.repository.UserRepository;
import com.pramod.social.request.LoginRequest;
import com.pramod.social.response.AuthResponse;
import com.pramod.social.service.CustomerUserDetailsService;
import com.pramod.social.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;
	@Autowired
	private  UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		User IsExist=userRepository.findByEmail(user.getEmail());
		if(IsExist!=null) {
			throw new Exception("This email is alread use with antother acccount");
			
			
		}
		
		User newUser=new User(); 
		  newUser.setId(user.getId());
		  newUser.setFirstName(user.getFirstName());
		  newUser.setLastName(user.getLastName()); 
		  newUser.setEmail(user.getEmail());
		 newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		 
		User saveUser=userRepository.save(newUser);
		Authentication authentication=new UsernamePasswordAuthenticationToken(saveUser.getEmail(), saveUser.getPassword());
		String  token= JwtProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token,"Register Success");
		return res;
		
	}
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication =authenticate(loginRequest.getEmail(),loginRequest.getPassword());
		String  token= JwtProvider.generateToken(authentication);
		AuthResponse res=new AuthResponse(token,"Login Success");
		
		return res;
	}
	private Authentication authenticate(String email, String password) {
	    UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
	    if (userDetails == null) {
	        throw new BadCredentialsException("Invalid email or password.");
	    }
	    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
	        throw new BadCredentialsException("Invalid email or password.");
	    }
	    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
