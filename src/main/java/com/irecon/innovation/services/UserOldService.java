package com.irecon.innovation.services;



import org.springframework.stereotype.Service;

import com.irecon.innovation.entity.User;

@Service
public interface UserOldService {

    public User loadUserByUsername(String username);

}
