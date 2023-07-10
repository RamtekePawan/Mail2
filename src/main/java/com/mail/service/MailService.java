package com.mail.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mail.entity.Mail;
import com.mail.entity.User;
import com.mail.repository.MailRepository;
import com.mail.repository.UserRepository;


@Service
public class MailService {
	
	@Autowired
	MailRepository mailRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public void saveMail(String toEmail,int fromUserId, Mail mail) {
		
		User toUser = userRepo.findByUserEmail(toEmail);
//		
//		System.out.println(toUser.getUserEmail());
		User fromUser = userRepo.findByUserId(fromUserId);

		Mail toMail = new Mail();
		toMail.setDateAndTime(LocalDateTime.now());
		toMail.setType("RECIEVED");
		toMail.setUser(toUser);
		toMail.setMessage(mail.getMessage());
		toMail.setSubject(mail.getSubject());
		
		//System.out.println("to Mail , saveMail : "+toMail.toString());
		mailRepo.save(toMail);
		
		Mail fromMail = new Mail();
		fromMail.setDateAndTime(LocalDateTime.now());
		fromMail.setType("SENT");
		fromMail.setUser(fromUser);
		fromMail.setMessage(mail.getMessage());
		fromMail.setSubject(mail.getSubject());
		
		mailRepo.save(fromMail);
	}
	
	public List<Mail> getMails(int userId){
		
		return mailRepo.findByUserRecieved(userId);
		
	}
	
	public List<Mail> getSentMails(int userId){
		
		return mailRepo.findByUserSent(userId);
		
	}
	
}
