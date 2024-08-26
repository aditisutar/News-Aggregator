package com.newsaggregator.config;

import com.newsaggregator.middleware.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class ApplicationConfiguration {
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login","/register","/h2-console/**","/verifyRegistration").permitAll()
                                .anyRequest().authenticated() // Permit all requests without authentication
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
                .httpBasic(httpBasic -> httpBasic.disable())  // Disable Basic Authentication
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login") // Specify the custom login page URL
//                                .permitAll() // Allow everyone to access the login page
//                )
                .logout(logout ->
                        logout
                                .permitAll() // Allow everyone to access the logout functionality
                )
                .headers(headers -> headers.frameOptions().disable())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();


    }


}
