/*
 * package com.irecon.innovation.entity;
 * 
 * import java.util.Collection; import java.util.Collections; import
 * java.util.List;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * public class CustomUserDetails implements UserDetails {
 * 
 * private String username; private String password; private
 * List<GrantedAuthority> authorities;
 * 
 * public CustomUserDetails(User user) { this.username = user.getName();
 * this.password = user.getPassword(); this.authorities =
 * Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")); }
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
 * return authorities; }
 * 
 * @Override public String getPassword() { return password; }
 * 
 * @Override public String getUsername() { return username; }
 * 
 * @Override public boolean isAccountNonExpired() { return true; // customize if
 * needed }
 * 
 * @Override public boolean isAccountNonLocked() { return true; }
 * 
 * @Override public boolean isCredentialsNonExpired() { return true; }
 * 
 * @Override public boolean isEnabled() { return true; } }
 */