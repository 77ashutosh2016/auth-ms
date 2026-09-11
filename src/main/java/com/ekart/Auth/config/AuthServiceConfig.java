package com.ekart.Auth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthServiceConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
    {
        http.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/login","/.well-known/jwks.json").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().denyAll()).headers(headers-> headers.frameOptions((frame-> frame.sameOrigin())));


        return http.build();


    }
}
