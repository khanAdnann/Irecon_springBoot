package com.irecon.innovation.controller;

import java.io.IOException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.irecon.innovation.entity.User;
import com.irecon.innovation.services.Impl.EmailService;
import com.irecon.innovation.services.Impl.KafkaProducerService;
import com.irecon.innovation.services.Impl.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private KafkaProducerService kafkaService;

    @GetMapping("/register_user")
    public String registerUser() {
        return "RegisterUser";
    }

    @GetMapping("/forget_password")
    public String forgetPass() {
        return "ForgetPassword";
    }

    
    @PostMapping("/reset_password")
    public ResponseEntity<?> resetpassword(@RequestParam("userid")String userId,
    		  @RequestParam(value = "password", required = false) String password){
    	System.out.println("userId"+password);
    	
    	User user = userService.getUserByUserid(userId);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        
        if(user != null)
        {
        	user.setPassword(password);
        }
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return new ResponseEntity<>("Password Reset Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed To Reset", HttpStatus.OK);
        }
    	
    	
    }
    
    @PostMapping("/register_user")
    public ResponseEntity<?> registerUser(
            @RequestParam("name") String name,
            @RequestParam("userid") String userid,
            @RequestParam("password") String password,
            @RequestParam("profilePicture") MultipartFile profilePicture,
            @RequestParam("email") String email) {

        User user = new User();
        user.setName(name);
        user.setUserid(userid);
        user.setPassword(password);
        user.setEmail(email);

        try {
            user.setProfilePicture(profilePicture.getBytes());
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to process profile picture", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        User newUser = userService.createUser(user);
		/*
		 * if (newUser != null) {
		 * 
		 * emailService.sendEmail(user.getEmail(), "Registration Successful",
		 * "Welcome your account has been created successfully! For More Info Contact Admin"
		 * ); return new ResponseEntity<>("User Registered Successfully ",
		 * HttpStatus.OK); } else { return new ResponseEntity<>("Failed To Register",
		 * HttpStatus.OK); }
		 */
        
        if (newUser != null) {
        //	kafkaService.sendUserCreateEvent(email);
            return new ResponseEntity<>("User Registered Successfully ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed To Register", HttpStatus.OK);
        }
    }

    @GetMapping(value = { "I-RECON", "/" })
    public String loginUser() {
        return "LoginUser";
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("userid") String userid, @RequestParam("password") String password) {
      
    	
    	User user = userService.loginUser(userid, password);
        if (user != null) {
        	
        	
        	
            return new ResponseEntity<>("Login Successfull", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("USER IS NOT REGISTER PLEASE CONTACT ADMIN", HttpStatus.OK);
        }
    }
    
    @GetMapping("/create_customer")
    public String CreateCustomer() {
    	return "CreateCustomer";
    }

    @GetMapping("/edit_user")
    public String editUser() {
        return "EditUser";
    }

    // Fetch user data for editing
    @GetMapping("/get_user_data")
    public ResponseEntity<User> getUserData(@RequestParam("userid") String userid) {
        User user = userService.getUserByUserid(userid);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update user data
    @PostMapping("/update_user")
    public ResponseEntity<?> updateUser(
            @RequestParam("name") String name,
            @RequestParam("userid") String userid,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture) {

        User user = userService.getUserByUserid(userid);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        user.setName(name);
        if (password != null && !password.isEmpty()) {
            user.setPassword(password);
        }

        if (profilePicture != null && !profilePicture.isEmpty()) {
            try {
                user.setProfilePicture(profilePicture.getBytes());
            } catch (IOException e) {
                return new ResponseEntity<>("Failed to process profile picture", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return new ResponseEntity<>("User Updated Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed To Update", HttpStatus.OK);
        }
    }
}