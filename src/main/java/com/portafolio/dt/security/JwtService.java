package com.portafolio.dt.security;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.util.*;
@Service public class JwtService {
 @Value("${app.jwt.secret}") String secret; @Value("${app.jwt.expiration-ms}") long expiration;
 SecretKey key(){return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));}
 public String generate(String u){return Jwts.builder().subject(u).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+expiration)).signWith(key()).compact();}
 public String username(String t){return Jwts.parser().verifyWith(key()).build().parseSignedClaims(t).getPayload().getSubject();}
 public boolean valid(String t){try{username(t);return true;}catch(Exception e){return false;}}
}
