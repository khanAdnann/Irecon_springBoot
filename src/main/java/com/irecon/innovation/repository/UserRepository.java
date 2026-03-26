package com.irecon.innovation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.irecon.innovation.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUseridAndPassword(String userid, String password);
	
	User findByUserid(String userid);

}
