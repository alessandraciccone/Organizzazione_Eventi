package com.example.Organizzazione_Eventi.security;

import entities.User;
import exceptions.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import payload.UserResponseDTO;
import service.UserService;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWWTTools jwwtTools;
    private final UserService userService;

    public JWTFilter(JWWTTools jwtTools, UserService userService) {
        this.jwwtTools = jwtTools;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // lascio passare se non c'è token (gestirà SecurityConfig)
            return;
        }

        String accessToken = authHeader.replace("Bearer ", "").trim();

        try {
            jwwtTools.verifyToken(accessToken);

            UUID userId = jwwtTools.extractIdFromToken(accessToken);
            UserResponseDTO found = userService.findById(userId);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(found, null, found.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            throw new UnauthorizedException("Token non valido o scaduto");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // escludo gli endpoint di autenticazione dal filtro
        return request.getServletPath().startsWith("/auth/");
    }
}