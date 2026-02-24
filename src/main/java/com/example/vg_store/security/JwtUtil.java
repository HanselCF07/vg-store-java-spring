package com.example.vg_store.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtil {

    private final Key key;

    public JwtUtil(@Value("${jwt.secret}") String secret) {        
        // Usar la cadena Base64 tal cual, en UTF-8
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims obtenerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String obtenerSubject(String token) {
        return obtenerClaims(token).getSubject();
    }

    public Object obtenerClaim(String token, String claimName) {
        return obtenerClaims(token).get(claimName);
    }

    public boolean esTokenValido(String token) {
        try {
            obtenerClaims(token);
            return true;
        } catch (JwtException e) {
            System.out.println("Error validando token: " + e.getMessage());
            return false;
        }
    }
}