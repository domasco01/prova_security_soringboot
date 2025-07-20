package domenico.ascolese.corso_security.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // Chiave segreta — in produzione mettila in env var o vault!
    private final String JWT_SECRET = "5QeTr2U6x9XbH6nZrFjYpLsNdMbXwVuZkqP3s8wE7tYhRdFuGiKjLmNoPqRsTuVw";

    private final SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

    // Durata del token (ms). Qui 24h.
    private final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000;

    // Genera il token dopo autenticazione
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(username)           // “body” del JWT
                .setIssuedAt(now)               // quando è stato emesso
                .setExpiration(expiryDate)      // validità
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    // Estrae username dal token (se valido)
    public String getUsernameFromJWT(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Controlla firma e scadenza
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}

