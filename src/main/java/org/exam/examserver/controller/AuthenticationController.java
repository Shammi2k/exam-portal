package org.exam.examserver.controller;

import java.security.Principal;

import org.exam.examserver.jwt.JwtRequest;
import org.exam.examserver.jwt.JwtResponse;
import org.exam.examserver.jwt.JwtUtils;
import org.exam.examserver.model.User;
import org.exam.examserver.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController
{
  private JwtUtils jwtUtils;
  private AuthenticationManager authenticationManager;
  private UserServiceImpl userService;

  @PostMapping("/generate-token")
  public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest)
    throws Exception
  {
    UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
    if (userDetails == null)
    {
      throw new Exception("User not found");
    }
    authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
    String token = jwtUtils.generateToken(userDetails);
    return ResponseEntity.ok(new JwtResponse(token));
  }

  @GetMapping("/current-user")
  public User currentUser(Principal principal)
  {
    return (User)userService.loadUserByUsername(principal.getName());
  }

  private void authenticate(String username, String password)
  {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
  }

  @Autowired
  public void setJwtUtils(JwtUtils jwtUtils)
  {
    this.jwtUtils = jwtUtils;
  }

  @Autowired
  public void setAuthenticationManager(AuthenticationManager authenticationManager)
  {
    this.authenticationManager = authenticationManager;
  }

  @Autowired
  public void setUserService(UserServiceImpl userService)
  {
    this.userService = userService;
  }
}
