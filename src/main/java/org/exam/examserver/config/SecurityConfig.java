package org.exam.examserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
  private JwtAuthenticationEntryPoint authenticationEntryPoint;
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception
  {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .cors(CorsConfigurer::disable)
      .authorizeHttpRequests((registry) -> registry
        .requestMatchers("/generate-token", "/error", "/user/")
        .permitAll()
        .requestMatchers(HttpMethod.OPTIONS, "/**")
        .permitAll()
        .anyRequest().authenticated())
      .exceptionHandling(handler -> handler.authenticationEntryPoint(authenticationEntryPoint))
      .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
    throws Exception
  {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Autowired
  public void setAuthenticationEntryPoint(JwtAuthenticationEntryPoint authenticationEntryPoint)
  {
    this.authenticationEntryPoint = authenticationEntryPoint;
  }

  @Autowired
  public void setJwtAuthenticationFilter(JwtAuthenticationFilter jwtAuthenticationFilter)
  {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }
}
