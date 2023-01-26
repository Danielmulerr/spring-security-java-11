package com.exercise.springsecurity.service;

import com.exercise.springsecurity.config.properties.Constants;
import com.exercise.springsecurity.util.TimeUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
public class JwtService {
    private static final String SECRET = "77217A25432A462D4A614E645267556B58703273357638782F413F4428472B4B";
    private static final int NOW = 0;

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    public String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(TimeUtils.getTimeAfterGivenMinutes(NOW))
                .setExpiration(TimeUtils.getTimeAfterGivenMinutes(Constants.TOKEN_EXPIRATION_IN_MINUTES))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (!isTokenExpired(token) && username.equals(userDetails.getUsername()));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
