//package org.example.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.Data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//
//@Component
//public class JWTUtils {
//
//    private final String jwtSecret;
//
//
//    @Autowired
//    private JWTUtils(@Value("${jwt.secret}") String jwtSecret) {
//        this.jwtSecret = jwtSecret;
//    }
//
//
//    public SecretKey getSecretKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String generateJwtToken(String email) {
//        Date data = Date.
//                from(LocalDateTime.
//                        now()
//                        .plusMinutes(30)
//                        .atZone(ZoneId.systemDefault())
//                        .toInstant());
//
//        return Jwts
//                .builder()
//                .subject(email)
//                .expiration(data)
//                .signWith(getSecretKey())
//                .compact();
//    }
//
//    public Jws<Claims> validateToken(final String token) {
//        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
//    }
//
//    public String generateRefreshToken(String email) {
//        Date data = Date.
//                from(LocalDateTime.
//                        now()
//                        .plusDays(30)
//                        .atZone(ZoneId.systemDefault())
//                        .toInstant());
//
//        return Jwts
//                .builder()
//                .subject(email)
//                .expiration(data)
//                .signWith(getSecretKey())
//                .compact();
//    }
//
//
//}
