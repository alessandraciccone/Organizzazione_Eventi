
/*
package com.example.Organizzazione_Eventi.security;


import entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWWTTools jwtTools;
    private final UserService userService;

    public JWTFilter(JWWTTools jwtTools, UserService userService) {
        this.jwtTools = jwtTools;
        this.userService = userService;

        @Override
        protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain
        filterChain)throw ServletException,
        IOException {

            //recuperiamo tokern
            String authHeader = request.hetHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer")) {
                throw new UnauthorizedException("token mancante");
            }
            //estriamo token
            String accessToken = authHeader.replace("Bearer", "");
            //verifichiamo validità token
            jwtTools.verifyToken(accessToken);
            //prendiamo id utente del token

            UUID userId = jwtTools.extractIdFromToken(accessToken);

            //prendiamo utente dal db

            User found = userService.findById(userId);

            // creo authentication
            Authentication authentication = new UsernamePasswordAuthenticationToken(found, null, found.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, respon00se00);
        }


        @Override
        protected boolean shouldNotFilter (httpServletRequest request)throw ServletException {
            return new AntiPatgMatcher().match("/auth/**", request.getServletPath());
        }
    }
*/