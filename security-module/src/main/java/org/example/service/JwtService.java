package org.example.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    private final PrivateKey privateKey;

    public JwtService(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public String generateJwtToken(String email) {
        Date data = Date.
                from(LocalDateTime.
                        now()
                        .plusMinutes(30)
                        .atZone(ZoneId.systemDefault())
                        .toInstant());

        return Jwts
                .builder()
                .subject(email)
                .expiration(data)
                .signWith(privateKey)
                .compact();
    }
}
