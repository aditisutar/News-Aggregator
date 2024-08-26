package com.newsaggregator.service.serviceImpl;

import com.newsaggregator.entity.Users;
import com.newsaggregator.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;
    @Value("${security.jwt.expiration-time}")
    private long jwtExpirationTime;

    @Override
    public String createJwtToken(Users authenticatedUser) {
        Map<String, Object> extraClaims=new HashMap<>();
        extraClaims.put("email",authenticatedUser.getEmail());
        extraClaims.put("role",authenticatedUser.getRole());
        return Jwts.builder()
                .claims(extraClaims)
                .subject(authenticatedUser.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+jwtExpirationTime))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public long getExpirationTime() {
        return jwtExpirationTime;
    }
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    @Override
    public String extractUserName(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    @Override
    public boolean isTokenValid(String jwt, UserDetails user) {
        final String username = extractUserName(jwt);
        return (username.equals(user.getUsername())) && !isTokenExpired(jwt);
    }
}
