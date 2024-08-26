package com.newsaggregator.service;

import com.newsaggregator.entity.UserPreference;

import java.util.Optional;

public interface UserPreferenceService {
    Optional<UserPreference> getPreferences(String email);
    UserPreference updatePreferences(String email, UserPreference userPreference);
}
