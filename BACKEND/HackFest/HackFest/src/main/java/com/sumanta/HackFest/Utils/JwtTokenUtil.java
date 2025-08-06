package com.sumanta.HackFest.Utils;

import com.sumanta.HackFest.Entities.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.util.Date;

import static io.jsonwebtoken.Jwts.builder;

@Component
public class JwtTokenUtil {
    private SecretKey SECRET_KEY;
    @PostConstruct
    public void init() {
        //SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.ES512);
        String secret = "my-very-secret-key-that-should-be-long-enough-to-meet-the-HS512-requirements";
        SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String GenerateToken(String userId, Role role) {
        return builder().
                setSubject(userId) // user can be govt, supplier, or client
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Role getRoleFromToken(String token) {
        String roleStr = getAllClaimsFromToken(token).get("role", String.class);
        return Role.valueOf(roleStr);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
