package com.newsaggregator.controller;

import com.newsaggregator.entity.Users;
import com.newsaggregator.model.LoginDto;
import com.newsaggregator.model.LoginResponse;
import com.newsaggregator.service.JwtService;
import com.newsaggregator.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/login")
    public LoginResponse autenticate(@Valid @RequestBody LoginDto loginDto){
        Users authenticatedUser= loginService.authenticateUser(loginDto);
           //create token
        String jwtToken=jwtService.createJwtToken(authenticatedUser);
        LoginResponse loginResponse=new LoginResponse(jwtToken,jwtService.getExpirationTime());
        return loginResponse;
    }
}
