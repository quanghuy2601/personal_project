package com.dusty.personal_project.Security.Jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static final String jwtSecret = "zbsdcESrPoaniLGdhoIVJPc6TmNMQFDFFKs3LeratXH0oKq2MfcaY7B3qMqJBu3P8";

    private static final long jwtExpirationMs = 86400000;

    private static final long jwtExpirationMsRefresh = 604800000;

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateTokenFromUsername(String username, UUID userid, String fullName, String roles) {
        Date date = new Date();
        return Jwts.builder()
                .claim("sub", username)
                .claim("iat", date)
                .claim("user_id", userid)
                .claim("fullname", fullName)
                .claim("roles", roles)
                .claim("exp", date.getTime() + jwtExpirationMs)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshTokenFromUsername(String username, UUID userid) {
        Date date = new Date();
        return Jwts.builder()
                .claim("sub", username)
                .claim("iat", date)
                .claim("user_id", userid)
                .claim("exp", date.getTime() + jwtExpirationMsRefresh)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            getClaimsFromToken(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String generateRefreshTokenFromUsername(String username, UUID userid, Date date) {
        return Jwts.builder()
                .claim("sub", username)
                .claim("iat", date)
                .claim("user_id", userid)
                .claim("exp", date)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Date getTokenExpiryFromJWT(String token) {
        return getClaimsFromToken(token).getExpiration();
    }
}
