package org.exam.examserver.config;

import java.io.IOException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.exam.examserver.jwt.JwtUtils;
import org.exam.examserver.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter
  extends OncePerRequestFilter
{
  private JwtUtils jwtUtils;
  private UserServiceImpl userService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException
  {
    final String authenticationHeader = request.getHeader("Authorization");
    String username = null;
    String jwtToken;

    if (authenticationHeader != null && authenticationHeader.startsWith("Bearer "))
    {
      jwtToken = authenticationHeader.substring(7);
      try
      {
        username = jwtUtils.extractUsername(jwtToken);
      }
      catch (ExpiredJwtException eje)
      {
        System.out.println("Jwt Token Expired : " + eje.getMessage());
      }
    }
    else
    {
      filterChain.doFilter(request, response);
      return;
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
    {
      final UserDetails userDetails = userService.loadUserByUsername(username);
      if (jwtUtils.validateToken(jwtToken, userDetails))
      {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
      else
      {
        throw new JwtException("Invalid JWT Token");
      }
    }
    filterChain.doFilter(request, response);
  }

  @Autowired
  public void setJwtUtils(JwtUtils jwtUtils)
  {
    this.jwtUtils = jwtUtils;
  }

  @Autowired
  public void setUserService(UserServiceImpl userService)
  {
    this.userService = userService;
  }
}
