package cl.javier.starwars.security;

import cl.javier.starwars.domain.AppUser;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service;
import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.time.Instant; import java.util.Date;

@Service
public class JwtService {
 private final SecretKey key; private final long expiration;
 public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration}") long expiration){this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));this.expiration=expiration;}
 public String create(AppUser u){Instant now=Instant.now();return Jwts.builder().subject(u.getUsername()).claim("role",u.getRole().name()).issuedAt(Date.from(now)).expiration(Date.from(now.plusSeconds(expiration))).signWith(key).compact();}
 public String username(String token){return claims(token).getSubject();}
 public boolean valid(String token,String username){Claims c=claims(token);return username.equalsIgnoreCase(c.getSubject())&&c.getExpiration().after(new Date());}
 private Claims claims(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();}
 public long expiration(){return expiration;}
}
