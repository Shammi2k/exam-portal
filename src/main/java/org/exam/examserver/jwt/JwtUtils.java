package org.exam.examserver.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils
{

  private String SECRET_KEY = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";

  public String extractUsername(String token)
  {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token)
  {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
  {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token)
  {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token)
  {
    return extractExpiration(token).before(new Date());
  }

  public String generateToken(UserDetails userDetails)
  {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject)
  {
    byte[] bytes = Base64.getDecoder().decode(SECRET_KEY);
    Key key = Keys.hmacShaKeyFor(bytes);
    io.jsonwebtoken.JwtBuilder builder = Jwts.builder();
    builder.setClaims(claims);
    builder.setSubject(subject);
    builder.setIssuedAt(new Date(System.currentTimeMillis()));
    builder.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10));
    builder.signWith(key, SignatureAlgorithm.HS256);
    return builder.compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails)
  {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}