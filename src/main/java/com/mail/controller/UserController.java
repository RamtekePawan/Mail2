package com.mail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mail.entity.User;
import com.mail.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	@PostMapping("/add-user") //Ok tested
	public String addUser(User user) {
		userServ.saveUser(user);
		return "User Added Successfully";
	}
	
	@PostMapping("/get-users")  
	public List<User> getAllUsers() {
		return userServ.getAllUser();
	}
	
	@RequestMapping("/login-user")
	public User getUserForLogin(@RequestParam String email, @RequestParam String password ) {
		
		User user = userServ.getLoginUser(email,password);
		if(user != null)
			return user;
		return null;
		
	}
	
}
