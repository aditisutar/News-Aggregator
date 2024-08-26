package com.newsaggregator.service;

import com.newsaggregator.entity.Users;
import com.newsaggregator.model.LoginDto;

public interface LoginService {
    Users authenticateUser(LoginDto loginDto);
}
