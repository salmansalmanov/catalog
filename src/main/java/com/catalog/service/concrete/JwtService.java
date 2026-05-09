package com.catalog.service.concrete;

import com.catalog.exception.custom.InvalidTokenException;
import com.catalog.model.entity.RefreshTokenEntity;
import com.catalog.model.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${spring.security.jwt.access-token.secret}")
    private String accessTokenSecret;

    @Value("${spring.security.jwt.refresh-token.secret}")
    private String refreshTokenSecret;

    @Value("${spring.security.jwt.access-token.expiration}")
    private long accessTokenExpiration;

    @Value("${spring.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    public String generateAccessToken(String username, Role role) {
        return buildToken(username, role, accessTokenExpiration, accessTokenSecret);
    }

    public String generateRefreshToken(String username, Role role) {
        return buildToken(username, role, refreshTokenExpiration, refreshTokenSecret);
    }

    private String buildToken(String username, Role role, long expiration, String secret) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role.name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(secret))
                .compact();
    }

    private Key getSigningKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String extractUsername(String token, boolean isRefreshToken) {
        return extractClaim(token, Claims::getSubject, isRefreshToken);
    }

    public String extractRole(String token, boolean isRefreshToken) {
        return extractAllClaims(token, isRefreshToken).get("role", String.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, boolean isRefreshToken) {
        final Claims claims = extractAllClaims(token, isRefreshToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, boolean isRefreshToken) {
        String secret = isRefreshToken ? refreshTokenSecret : accessTokenSecret;
        try {
            return Jwts.parser()
                    .verifyWith((SecretKey) getSigningKey(secret))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token: " + e.getMessage());
        }
    }

    public boolean isTokenValid(String token, String username, boolean isRefreshToken) {
        final String extractedUsername = extractUsername(token, isRefreshToken);
        return (extractedUsername.equals(username) && !isTokenExpired(token, isRefreshToken));
    }

    public boolean isTokenExpired(String token, boolean isRefreshToken) {
        return extractClaim(token, Claims::getExpiration, isRefreshToken).before(new Date());
    }

    public void checkRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        if (refreshTokenEntity.getRevoked()) {
            throw new InvalidTokenException("Refresh token is revoked");
        }
        if (refreshTokenEntity.getExpiration().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException("Refresh token is expired");
        }
    }
}
