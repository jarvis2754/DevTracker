package com.devtracker.DevTracker.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    public static final String SECRET_KEY = "a3fZ9v!L2r@Q7$eXpT8#sVuYkN1cMwRzG6dHjKlPo9AbCdEfGhIjKlMnOpQrStUv";
    public static final long EXPIRATION_TIME= 1000 * 60 *60 *10;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token){
        return parseToken(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            parseToken(token);
            return true;
        }catch (JwtException e){
            System.out.println("Token error: "+e.getMessage());
            return false;
        }
    }

    private Jws<Claims> parseToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);

    }
}
