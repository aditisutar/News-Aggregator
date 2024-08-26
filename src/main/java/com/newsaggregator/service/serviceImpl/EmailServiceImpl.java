package com.newsaggregator.service.serviceImpl;

import com.newsaggregator.exception.EmailServiceexception;
import com.newsaggregator.service.EmailService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
   private Logger logger;


    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Override
    @Async
    public void  sendEmail(String to, String subject, String body)  {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom(" aditi.sutar.prof@gmail.com");
            mailSender.send(message);
            throw new EmailServiceexception("Email not sent");
        }
        catch (EmailServiceexception e){
            logger.error(e.getMessage());
        }

    }
}
