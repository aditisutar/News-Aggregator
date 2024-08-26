package com.newsaggregator.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDto {
    private String email;
    private String password;
}
