package com.eduproctor.tut.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.stream.DoubleStream;

@Component
public class JwtUtils {

//    @Value("${app.jwtSecret}")
//    private String jwtSecret;

    private static final String SECRET_KEY = "3QlZzWf/CmuVB08x/9aQIjclXkr7ucEPaS7McEgk2Nw=";

    @Value("${app.jwtExpirationMs}")
    private Long jwtExpirationMs;

    //Generate JWT Token
    public String generateJwtToken(String userName){

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationMs)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }

    //GEt username from JWT Token
    public String getUserNameFromJwtToken(String token){

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e){
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }
}
