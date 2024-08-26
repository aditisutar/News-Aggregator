package com.newsaggregator.repo;

import com.newsaggregator.entity.UserPreference;
import com.newsaggregator.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrefernceRepo extends JpaRepository<UserPreference,Long> {
    UserPreference findByUser(Users user);
}
