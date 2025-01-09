package com.stockPrice.app.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Generate a strong secret key
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Token validity period (e.g., 10 hours)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    // Generate token based on the Authentication object
    public String generateToken(Authentication authentication) {
        String username = authentication.getName(); // Extract username from Authentication

        return Jwts.builder()
                .setSubject(username)  // Set the username as the subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 10 hours expiry
                .signWith(SECRET_KEY)  // Sign with a secure secret key
                .compact();
    }

    // Extract username from the token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Validate the token
    public boolean isTokenValid(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // Check if the token is expired
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Extract claims from the token
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Use the secure secret key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
