package com.newsaggregator.repo;

import com.newsaggregator.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Long> {
    public VerificationToken findByToken(String token);


}
