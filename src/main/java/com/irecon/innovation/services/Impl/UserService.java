package com.irecon.innovation.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecon.innovation.entity.User;
import com.irecon.innovation.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User loginUser(String userid, String password) {
		return userRepository.findByUseridAndPassword(userid, password);
	}
	
	 // Fetch user by userid
    public User getUserByUserid(String userid) {
        return userRepository.findByUserid(userid);
    }

    // Update user
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
