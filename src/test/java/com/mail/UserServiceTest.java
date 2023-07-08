package com.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mail.entity.User;
import com.mail.repository.UserRepository;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserRepository userRepo;
	
	@Test
	void addUser_test() {
		User u = new User();
		u.setAdmin(true);
		u.setUserEmail("admin@gmail.com");
		u.setUserName("admin");
		u.setUserPassword("admin");
		
		userRepo.save(u);
	}
	
	
	

}
