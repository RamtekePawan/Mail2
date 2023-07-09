package com.mail.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mail.entity.User;
import com.mail.repository.UserRepository;
import com.mail.service.UserService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userServ;
	
	@PostMapping("/add-user") //Ok tested
	public String addUser(@RequestBody User user) {
		System.out.println(user.getUserName() + " "+ user.getUserEmail());
		userServ.saveUser(user);
		return "User Added Successfully";
	}
	
	@PostMapping("/get-users")  
	public List<User> getAllUsers() {
		return userServ.getAllUser();
	}
	
	
	@GetMapping("/login-user")
	public ResponseEntity<?> getUserForLogin(HttpServletRequest request) {
	    try {

			String email = request.getParameter("email");
			String password = request.getParameter("password");
			System.out.println(email+ "  "+password);
			User user = userServ.getLoginUser(email,password);
			
			if(user != null)
				
				return ResponseEntity.ok(user);
			
			else
				throw new Exception();
	    } catch (Exception e) {
	        String errorMessage = "An error occurred while retrieving the User List.";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}
//	
//	@GetMapping("/{id}")
//    public ResponseEntity<?> getByIdRequest(@PathVariable("id") int id) {
//        final Model model = userRepo.findByUserId(id).orElse(null);
//        return model == null ? ResponseEntity.badRequest().body("ID not found") : ResponseEntity.ok(model);
//    }
//	@GetMapping("/{userId}")
//	public User getUser(@PathVariable String userId) {
//		return userServ.getLoginUser(userId);
//	}

	

	
}
