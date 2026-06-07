package com.jpa.fooddelivery.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 15 * 60 * 1000;
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000;
    private static final String SECRET = "nvsnvonfvklsnvlkxlkvnlkxnflvknfnvoxnrovno";
    private static final String REFRESH_TOKEN = "Refresh-Token";
    private static final String ACCESS_TOKEN = "ACCESS-Token";

    public String generateToken(String name, boolean isAccessToken) {

        long EXPIRATION_TIME = isAccessToken ? ACCESS_TOKEN_EXPIRATION_TIME : REFRESH_TOKEN_EXPIRATION_TIME;
        String tokenType = isAccessToken ? ACCESS_TOKEN : REFRESH_TOKEN;
        Map<String, Object> claims = new HashMap<>();
        claims.put("typ", tokenType);

        return Jwts.builder()
                .subject(name)
                .claims(claims)
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

    public boolean isRefreshToken(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String tokenType = claims.get("typ").toString();
        return tokenType.equals(REFRESH_TOKEN);
    }

    public boolean isAccessToken(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String tokenType = claims.get("typ").toString();
        return tokenType.equals(ACCESS_TOKEN);
    }
}
