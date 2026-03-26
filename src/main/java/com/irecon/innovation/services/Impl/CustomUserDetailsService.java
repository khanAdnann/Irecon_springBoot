/*
 * package com.irecon.innovation.services.Impl;
 * 
 * 
 * 
 * import java.util.Collections; import
 * org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * @Service public class CustomUserDetailsService implements UserDetailsService
 * {
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { // Example only: Replace with DB user lookup
 * logic if ("admin".equals(username)) { return new User("admin",
 * "$2a$10$Dow1MJmClL9MXFvM69MXYOoiM26n0GnGn7LuwRXEnMCtEkL3AZq2y", // 'admin123'
 * bcrypt Collections.emptyList()); } else { throw new
 * UsernameNotFoundException("User not found"); } } }
 */