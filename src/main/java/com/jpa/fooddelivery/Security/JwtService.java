package com.jpa.fooddelivery.Security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final long EXPIRATION_TIME = 16 * 60 * 1000;
    private static final String SECRET = "nvsnvonfvklsnvlkxlkvnlkxnflvknfnvoxnrovno";

    public String generateToken(String name) {

        return Jwts.builder()
                .subject(name)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }

    public String getNameFromToken(String token) {

        return Jwts.parser().setSigningKey(SECRET.getBytes()).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parser().setSigningKey(SECRET.getBytes()).build().parseSignedClaims(token);
            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {

        Date expiration = Jwts.parser()
                .setSigningKey(SECRET.getBytes()).build()
                .parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
