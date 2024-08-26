package com.newsaggregator.helper;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class LoggedEmail {
    public String getLoggedInEmail() throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null)
            throw new UsernameNotFoundException("User not found");
        return authentication.getName();
    }
}
