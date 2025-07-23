package com.pramod.social.config;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

	private static SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        try {
            @SuppressWarnings("deprecation")
			String jwt = Jwts.builder()
                    .setIssuer("Pramod")
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 86400000))
                    .claim("email", auth.getName())
                    .signWith(key)
                    .compact();

            return jwt;
        } catch (Exception e) {
            // Log exception details
            System.err.println("Error generating token: " + e.getMessage());
            
            return null;
        }
    }
	
	public static String getEmailFromJwtToken(String jwt) {
		
		jwt=jwt.substring(7);
		Claims claims=Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String  email=String.valueOf(claims.get("email"));
		
				
		
		return email;
	}
}
