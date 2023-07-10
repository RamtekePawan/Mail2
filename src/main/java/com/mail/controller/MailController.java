package com.mail.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mail.entity.Mail;
import com.mail.entity.User;
import com.mail.service.MailService;

@RestController
@CrossOrigin("*")
public class MailController {
	
	@Autowired
	private MailService mailServ;
	
	@PostMapping("/send-mail")
	public String sendMail( HttpServletRequest request ) {
		
		
		String toEmail =request.getParameter("receiver_email");
		int senderId =Integer.parseInt(request.getParameter("sender_id"));
		String message = request.getParameter("message");
		String subject = request.getParameter("subject");
		
		System.out.println(senderId + "  "+ toEmail +" "+ message+" "+subject);
		
		Mail mail = new Mail();
		mail.setMessage(message);
		mail.setSubject(subject);
		
		mailServ.saveMail(toEmail, senderId, mail);
		
		return "Added Successfully!!!";
	}
	
	@GetMapping("/recieve")  // done
	public ResponseEntity<?> get(HttpServletRequest request) {
	    try {
	        int userId = Integer.parseInt(request.getParameter("userId"));
	        System.out.println("User ID: " + userId);

	        List<Mail> list = mailServ.getMails(userId);
	        for (Mail mail : list) {
	            System.out.println(mail.getMessage());
	        }

	        return ResponseEntity.ok(list);
	    } catch (Exception e) {
	        String errorMessage = "An error occurred while retrieving the mail list.";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}
	
	@GetMapping("/sent")
	public ResponseEntity<?> getSentMsg(HttpServletRequest request) {
	    try {
	        int userId = Integer.parseInt(request.getParameter("userId"));
	        System.out.println("User ID: " + userId);

	        List<Mail> list = mailServ.getSentMails(userId);
	       
	        return ResponseEntity.ok(list);
	    } catch (Exception e) {
	        String errorMessage = "An error occurred while retrieving the mail list.";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}
	

}
