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
@CrossOrigin
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
	        System.out.println(e.getMessage());
	        String errorMessage = "An error occurred while retrieving the mail list.";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}
	
//	@GetMapping("/recieve")
//	public List<Mail> get( HttpServletRequest request){
//		int userId =Integer.parseInt(request.getParameter("userId")); 
//		System.out.println("User ID : "+userId);
//	List<Mail> list = null;
//	try {
//		list = mailServ.getMails(userId);
//		 for (Mail mail : list) {
//				System.out.print(mail.getMessage());
//			}
//	        
//		return list;
//	} catch (Exception e) {
//	System.out.println(e.getMessage())	;
//	}
//	
//	
//	for (Mail mail : list) {
//		System.out.println(mail.getMessage());
//	}
//		return list;
//	}
//	
	
	//@PostMapping("/recieve")
//	public ResponseEntity<?> getItemList(HttpServletRequest request) {
//		
//	     try {
//		int userId = Integer.parseInt(request.getParameter("userId")); 
//		
//	        	
//	        	List<Mail> list = null;
//	        	list = mailServ.getMails(userId);
//	        	
//	            if (list.isEmpty()) {
//	                // Return an empty list with HTTP status 204 No Content
//	                return ResponseEntity.status(HttpStatus.OK).body("No Data Found!");
//	            } else {
//	                // Return the list of items with HTTP status 200 OK
//	                return ResponseEntity.status(HttpStatus.OK).body(list);
//	            }
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
//	        } catch (Exception e) {
//	            // Handle any exceptions that occur during the process
//	            // Return an error message with HTTP status 500 Internal Server Error
//	        	System.out.println(e.getMessage());
//	            String errorMessage = "An error occurred while retrieving the item list.";
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
//	        }
//		}
	
//	//@GetMapping("/recieve")
//	public ResponseEntity<?> getItemList(@RequestParam("userId") int userId) {
//	    try {
//	        List<Mail> list = mailServ.getMails(userId);
//	        
//	        for (Mail mail : list) {
//				System.out.print(mail.getMessage());
//			}
//	        
//	        if (list.isEmpty()) {
//	            // Return an empty list with HTTP status 204 No Content
//	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Data Found!");
//	        } else {
//	            // Return the list of items with HTTP status 200 OK
//	            return ResponseEntity.status(HttpStatus.OK).body(list);
//	        }
//	    } catch (Exception e) {
//	        // Handle any exceptions that occur during the process
//	        // Return an error message with HTTP status 500 Internal Server Error
//	        System.out.println(e.getMessage());
//	        String errorMessage = "An error occurred while retrieving the item list.";
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
//	    }
//	}

}
