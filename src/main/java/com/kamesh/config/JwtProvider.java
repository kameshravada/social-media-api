package com.kamesh.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
//import jakarta.persistence.criteria.From;

public class JwtProvider {
	private static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());
	
	public static String generateToken(Authentication auth) {
		
		String jwt = Jwts.builder()
						.issuer("kamesh")
						.issuedAt(new Date())
						.expiration(new Date(new Date().getTime()+86400000))
//						.setIssuer("kamesh")
//						.setIssuedAt(new Date())
//						.setExpiration(new Date(new Date().getTime()+86400000))
						.claim("email", auth.getName())
						.signWith(key)
						.compact();
		return jwt;	
	}
	
	public static String getEmailFromJwt(String jwt) {
//		removing Bearer 
		jwt=jwt.substring(7);
		
		@SuppressWarnings("deprecation")
		Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String email = String.valueOf(claims.get("email"));
		
		return email;
	}
}
