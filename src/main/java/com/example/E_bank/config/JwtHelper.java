package com.example.E_bank.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

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
                .expiration()
                .compact();
    }

//    public static String extractUsername(String token) {
//        return getTokenBody(token).getSubject();
//    }
//    private static Claims getTokenBody(String token) {
//
//    }
}
