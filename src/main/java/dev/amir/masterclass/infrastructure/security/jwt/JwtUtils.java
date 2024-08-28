package dev.amir.masterclass.infrastructure.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Service
public class JwtUtils {
    private static final String SecretKey = "269b9a3babd27bc84bed9705a6a6325da852f77918029a4be31f81502c8266ad";

    public String issueToken(String subject) {
        return issueToken(subject, Map.of());
    }

    public String issueToken(String subject, String... scopes) {
        return issueToken(subject, Map.of("scopes", scopes));
    }


    public String issueToken(String subject, Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer("amirgolmoradi.com")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.DAYS)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public String getSubjectFromToken(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SecretKey.getBytes());
    }

    public boolean isTokenValid(String jwtToken, String username) {
        String subject = getSubjectFromToken(jwtToken);
        return subject.equals(username) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        Date today = Date.from(Instant.now());
        return getClaims(jwtToken).getExpiration().before(today);
    }
}
