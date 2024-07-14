package com.example.E_bank.config;

import com.example.E_bank.exeption.AccessDeniedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;


import java.security.Key;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JwtHelper {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final int MINUTES =10;
    public static String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    public static String extractUsername(String token) {
        return getTokenBody(token).getSubject();
    }
    private static Claims getTokenBody(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (SignatureException | ExpiredJwtException e) { // Invalid signature or expired token
            throw new AccessDeniedException("Access denied: " + e.getMessage());
        }
    }
    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) ;
    }

}
