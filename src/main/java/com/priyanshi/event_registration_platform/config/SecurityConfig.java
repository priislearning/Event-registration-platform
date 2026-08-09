package com.priyanshi.event_registration_platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
@Configuration//spring this class knows how to create objects for the application
public class SecurityConfig {

    @Bean//whenever anyone ask for passwordencoder call this method once and keep the object
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests(auth->{
         auth
                 .requestMatchers("/api/auth/register","/api/auth/login")
                 .permitAll();
         auth
                 .anyRequest()//any req that didnt match the prev rule
                 .authenticated();
        });
        return http.build();//converts configuration to actual rules
    }

}