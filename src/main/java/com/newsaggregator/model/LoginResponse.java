package com.newsaggregator.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {
    private String jwtToken;
    private long expirationTime;
}
