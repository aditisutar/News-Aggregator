package com.newsaggregator.service.serviceImpl;

import com.newsaggregator.entity.UserPreference;
import com.newsaggregator.entity.Users;
import com.newsaggregator.exception.NoUserPreferenceFoundException;
import com.newsaggregator.repo.UserPrefernceRepo;
import com.newsaggregator.repo.UserRepo;
import com.newsaggregator.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserPrefernceRepo userPrefernceRepo;
    @Override
    public Optional<UserPreference> getPreferences(String email) {
        Users user=userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User name not found"));
        UserPreference userPreference=userPrefernceRepo.findByUser(user);
        return Optional.ofNullable(Optional.of(userPreference).orElseThrow(() -> new NoUserPreferenceFoundException("No user preference found")));
    }

    @Override
    public UserPreference updatePreferences(String email, UserPreference userPreference) {
        Users user=userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User name not found"));
     //   user.setUserPreference(userPreference);
          userPreference.setUser(user);
        userRepo.save(user);
        return userPrefernceRepo.save(userPreference);
    }
}
