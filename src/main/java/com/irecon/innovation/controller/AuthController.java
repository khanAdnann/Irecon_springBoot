package com.irecon.innovation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.irecon.innovation.entity.User;
import com.irecon.innovation.model.AuthResponse;
import com.irecon.innovation.services.Impl.UserService;
import com.irecon.innovation.utility.JwtUtil;

@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    // UI login page
	/*
	 * @GetMapping(value = { "I-RECON", "/" }) public String loginUser() { return
	 * "LoginUser"; }
	 */

    // JWT Login Endpoint
    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestParam("userid") String userid,
                                       @RequestParam("password") String password) {

        System.out.println("Login Request: " + userid);

        // validate user using your DB service
        User user = userService.loginUser(userid, password);

        if (user == null) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
        }

        // authenticate with Spring Security
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userid, password)
        );

        // load user details for JWT claims
        UserDetails userDetails = userDetailsService.loadUserByUsername(userid);

        // generate JWT token
        String token = jwtUtil.generateToken(userDetails.getUsername());
        System.out.println("Token Generated: " + token);
        
       Boolean Isvalid = jwtUtil.validateToken(token, userDetails);
       
       System.out.println("Token Valid "+Isvalid);

        // return JSON response
        AuthResponse authResponse = new AuthResponse(token);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
