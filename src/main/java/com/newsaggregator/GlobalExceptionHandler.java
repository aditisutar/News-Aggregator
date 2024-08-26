package com.newsaggregator;

import com.newsaggregator.exception.EmailAlreadyExistsException;
import com.newsaggregator.exception.EmailServiceexception;
import com.newsaggregator.exception.NoUserPreferenceFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailServiceexception.class)
    public ResponseEntity<String> handleEmailAlreadyExistsException(EmailServiceexception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoUserPreferenceFoundException.class)
    public ResponseEntity<String> handleNoUserPreferenceFoundException(NoUserPreferenceFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
