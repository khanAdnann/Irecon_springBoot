
  package com.irecon.innovation.config;
  
  import org.springframework.context.annotation.Bean; import
  org.springframework.context.annotation.Configuration; import
  org.springframework.security.authentication.AuthenticationManager; import
  org.springframework.security.config.annotation.authentication.configuration.
  AuthenticationConfiguration; import
  org.springframework.security.config.annotation.web.configuration.
  EnableWebSecurity; import
  org.springframework.security.config.http.SessionCreationPolicy; import
  org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder; import
  org.springframework.security.web.SecurityFilterChain; import
  org.springframework.security.web.authentication.
  UsernamePasswordAuthenticationFilter;
  
  import com.irecon.innovation.utility.JwtRequestFilter;
  
  @Configuration
  
  @EnableWebSecurity public class SecurityConfig {
  
  private final JwtRequestFilter jwtFilter;
  
  public SecurityConfig(JwtRequestFilter jwtFilter) { this.jwtFilter =
  jwtFilter; }
  
  @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
  Exception {
  
  http.csrf().disable() .authorizeHttpRequests(auth -> auth
		  .anyRequest().permitAll()  ) .sessionManagement(sess ->
  sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
  .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  
  return http.build(); }
  
  

  
/*
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 */
  @Bean
  public PasswordEncoder passwordEncoder() {
      return NoOpPasswordEncoder.getInstance();
  }
  
  @Bean public AuthenticationManager
  authenticationManager(AuthenticationConfiguration config) throws Exception {
  
  return config.getAuthenticationManager(); } }
 