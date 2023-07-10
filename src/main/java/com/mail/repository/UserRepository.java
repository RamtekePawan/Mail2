package com.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mail.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserId(int userId);
	
	public User findByUserEmail(String userEmail);
	
	public User findByUserEmailAndUserPassword(String userEmail,String userPassword);
}
