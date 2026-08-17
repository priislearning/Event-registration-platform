package com.priyanshi.event_registration_platform.security;

import com.priyanshi.event_registration_platform.model.User;
import com.priyanshi.event_registration_platform.repository.UserRepository;
import com.priyanshi.event_registration_platform.service.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println(">>> JwtAuthenticationFilter is running");

        String authHeader = request.getHeader("Authorization");
        System.out.println("Header: " + authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {

            String jwt = authHeader.substring(7);
            System.out.println("JWT: " + jwt);
            String email = jwtService.extractUsername(jwt);
            System.out.println("Email: " + email);
            User user = userRepository.findByEmail(email).orElse(null);
            System.out.println("User: " + user);
            System.out.println("Valid Token: " + jwtService.isTokenValid(jwt, user));
            if (user != null
                    && jwtService.isTokenValid(jwt, user)
                    && SecurityContextHolder.getContext().getAuthentication() == null) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                Collections.emptyList()
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            // Invalid or expired token.
            // Don't authenticate the user.
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}