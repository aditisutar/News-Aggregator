package com.newsaggregator.service.serviceImpl;

import com.newsaggregator.entity.Users;
import com.newsaggregator.model.LoginDto;
import com.newsaggregator.repo.UserRepo;
import com.newsaggregator.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public Users authenticateUser(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        ));
        return userRepo.findByEmail(loginDto.getEmail()).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
