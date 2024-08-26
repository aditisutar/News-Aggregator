package com.newsaggregator.service;

import com.newsaggregator.exception.EmailServiceexception;

public interface EmailService {
    public void sendEmail(String to,String subject, String body) throws EmailServiceexception;
}
