package com.nsiagoassur.api.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class JwtUtil {


   private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
   private static final long EXPIRATION_TIME = 86400000; // 24 heures

    public static  String generateToken(String username, String nom, String prenom, String role) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .addClaims(Map.of(
                        "nom", nom,
                        "prenom", prenom,
                        "role", role
                ))
                .signWith(SECRET_KEY)
                .compact();
    }

    public static  Claims validateToken(String token) {
         return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}