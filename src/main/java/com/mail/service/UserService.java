package com.mail.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mail.entity.Mail;
import com.mail.entity.User;
import com.mail.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public List<User> getAllUser(){
		return userRepo.findAll();
	}
	
	public void saveUser(User user) {
		userRepo.save(user);
	}
	
	public User getLoginUser(String email, String pass) {
		
		
		return userRepo.findByUserEmailAndUserPassword(email,pass);
		//
	}
	

	
//	public String sendEmail(Mail mail,int fromUserId, int toUserId) {
//		
//		User fromUser = userRepo.findByUserId(fromUserId);
//		User toUser = userRepo.findByUserId(toUserId);
//		
//		if(toUser == null) 
//			return null;
//		Mail fromMail = new Mail();
//		fromMail.setDateAndTime(LocalDateTime.now());
//		fromMail.setMessage(mail.getMessage());
//		fromMail.setSubject(mail.getSubject());
//		fromMail.setType("SEND");
//		fromMail.setUser(fromUser);
//	
//		
//	}
	
}
