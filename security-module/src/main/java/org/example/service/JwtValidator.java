package org.example.service;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
@Service
public class JwtValidator {

    private final PublicKey publicKey;

    public JwtValidator(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public boolean validationToken(String token) {
        try {
            Jwts.parser().verifyWith(publicKey).build().parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
