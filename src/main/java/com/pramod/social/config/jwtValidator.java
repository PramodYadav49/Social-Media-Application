package com.pramod.social.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt=request.getHeader(JwtConstant.JWT_HEADER);
		if(jwt!=null) {
			try {
			    String email = JwtProvider.getEmailFromJwtToken(jwt);
			    if (email != null) {
			        List<GrantedAuthority> authorities = new ArrayList<>();
			        // Optionally, extract roles/authorities from the JWT and add them to the list
			        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
			        SecurityContextHolder.getContext().setAuthentication(authentication);
			    } else {
			        // Handle case where email is null
			        System.err.println("Email claim is missing in the token");
			    }
			} catch (Exception e) {
			    // Log the exception with specific information
			    System.err.println("Error parsing or validating token: " + e.getMessage());
			    // Optionally, you could throw a specific exception or handle the response accordingly
			}
		}
		filterChain.doFilter(request, response);
		
	}

	

}
