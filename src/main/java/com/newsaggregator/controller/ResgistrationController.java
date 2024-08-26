package com.newsaggregator.controller;

import com.newsaggregator.entity.Users;
import com.newsaggregator.model.UserModel;
import com.newsaggregator.service.EmailService;
import com.newsaggregator.service.ResgistrationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ResgistrationController {
    @Autowired
    private ResgistrationService resgistrationService;
    @Autowired
    private EmailService emailService;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @PostMapping("/register")
public Users register(@Valid @RequestBody UserModel userModel,HttpServletRequest request){
    Users users= resgistrationService.registerUser(userModel);
    String token= UUID.randomUUID().toString();
    String applicationURL=getApplicationURL(request)+"/verifyRegistration?token="+token;
    resgistrationService.createVerificationToken(users,token);
        System.out.println("Verification url: "+applicationURL);
        return users;
    }
    @PostMapping("/verifyRegistration")
   public String verifyRegistration(@RequestParam String token){
        Boolean isValid= resgistrationService.validateTokenANdEnableUser(token);
        if(!isValid){
            return "Registration not successfull";
        }
        return "Registration successfull";
   }
    public String getApplicationURL(HttpServletRequest request){
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}
