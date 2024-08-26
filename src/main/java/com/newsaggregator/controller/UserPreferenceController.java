package com.newsaggregator.controller;

import com.newsaggregator.entity.UserPreference;
import com.newsaggregator.service.UserPreferenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/preferences")
public class UserPreferenceController {
    @Autowired
    private UserPreferenceService userPreferenceService;

    @GetMapping
    public ResponseEntity<Optional<UserPreference>> getPreferences() {
        String username = getLoggedInEmail();
        return new ResponseEntity<>(userPreferenceService.getPreferences(username), HttpStatus.OK) ;
    }

    @PutMapping
    public ResponseEntity<UserPreference> updatePreferences(@Valid @RequestBody UserPreference userPreference) {
        String email = getLoggedInEmail();
        return new ResponseEntity<>(userPreferenceService.updatePreferences(email, userPreference),HttpStatus.OK) ;
    }

    private String getLoggedInEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
