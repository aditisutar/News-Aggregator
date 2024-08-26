package com.newsaggregator.service;

import com.newsaggregator.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    public String createJwtToken(Users user);
    public long getExpirationTime();

    String extractUserName(String jwt);

    boolean isTokenValid(String jwt, UserDetails user);
}
