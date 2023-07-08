package com.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mail.entity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserId(int userId);
	
	public User findByUserEmail(String userEmail);
	
	public User findByUserEmailAndUserPassword(String userEmail,String userPassword);
}
