package com.pramod.social.config;

import java.lang.reflect.Array;
import java.util.Collection;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Management;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import io.jsonwebtoken.lang.Arrays;
import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {
	/*1 Session Management: Configured as stateless, suitable for REST APIs.
	  2 Authorization: /api* endpoints require authentication, while other endpoints are open.
	  3	Custom JWT Filter: A placeholder for a JWT validation filter, which should be implemented.
	  4	CSRF Disabled: Appropriate for stateless REST APIs.
	  5	Password Encoding: Uses BCrypt for secure password hashing.*/
	
	@Bean
	SecurityFilterChain securityFillChain(HttpSecurity http) throws Exception{
		http.sessionManagement(management->management
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/api/**").authenticated()
				.anyRequest().permitAll()).addFilterBefore(new jwtValidator(),BasicAuthenticationFilter.class)
		.csrf(csrf->csrf.disable())
		.cors(cors->cors.configurationSource( corsConfigurationSource()));
		
		return http.build();
		
	}
	private CorsConfigurationSource corsConfigurationSource() {
	    return new CorsConfigurationSource() {
	        @Override
	        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
	            CorsConfiguration cfg = new CorsConfiguration();
	            cfg.setAllowedOrigins(java.util.Arrays.asList("http://localHost:3000/"));
	          cfg.setAllowedMethods(java.util.Collections.singletonList("*"));
	          cfg.setAllowCredentials(true);
	          cfg.setAllowedHeaders(java.util.Collections.singletonList("*"));
	          cfg.setExposedHeaders(java.util.Arrays.asList("Authorization"));
	          cfg.setMaxAge(3600l);
	            return cfg; // Corrected to return the configuration instead of null
	        }
	    };
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
