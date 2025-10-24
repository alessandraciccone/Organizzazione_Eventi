

package com.example.Organizzazione_Eventi.security;

import entities.User;
import exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JWWTTools {
    @Value("${jwt.secret}")
    private String secret;

    //facciamo durare il token 7 giorni

    private static final long EXPIRATION_TIME= 1000*60*60*24*7;


    //creo jwt

    public String createToken(User user){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .subject(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();

    }

    //verifico validità token

    public void verifyToken(String accessToken){
        try{
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(accessToken);
        } catch(Exception ex){
            throw  new UnauthorizedException("token non valido o sacduto"
            );
        }
    }

    //estraggo uiid utente dal subject token

    public UUID extractIdFromToken(String accessToken){
        return UUID.fromString(Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(accessToken)
                .getPayload()
                .getSubject());
    }

}
