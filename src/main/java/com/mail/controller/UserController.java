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
import org.springframework.web.bind.annotation.PutMapping;
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
	
//	@PostMapping("/get-users")  
//	public List<User> getAllUsers() {
//		return userServ.getAllUser();
//	}
	
	@GetMapping("/delete-user")
	public ResponseEntity<?> deleteUser(HttpServletRequest request) {
	    try {
	    	
	    	String email = request.getParameter("email");
	    	userServ.deleteUser(email);
	    } catch (Exception e) {
	        String errorMessage = "An error occurred while retrieving the User List.";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
		return null;
	}
	
//	@PutMapping("/user")
//	public User updateUser(@RequestBody User user) {
//		return this.userService.updateUser(user);
//	}
	
	
	@PostMapping("/update-user2")
	public ResponseEntity<?> updateUser2(@RequestBody User newUser) {
	    // Get the user from the database by their ID
		
		System.out.println("Updated ::::: "+newUser.getUserEmail()+"  "+ newUser.getUserName());
		
	    User user = userRepo.findByUserEmail(newUser.getUserEmail());
	    System.out.println("existing user :"+ user.getUserName());
	    
	    // If the user doesn't exist, return null
	    if (user == null) {
	        return null;
	    }

	    // Update the user's information with the new information from the request body
	    user.setUserName(newUser.getUserName());
	    user.setUserEmail(newUser.getUserEmail());
	    user.setUserPassword(newUser.getUserPassword());

	    // Save the updated user to the database
	    userRepo.save(user);

	    // Return the updated user
	    return ResponseEntity.ok(user);
	}
	

	@PostMapping("/get-users")
	public ResponseEntity<?> getAllUsers(HttpServletRequest request) {
	    try {

	    	List<User> list = userServ.getAllUser();
			
			if(list != null)
				
				return ResponseEntity.ok(list);
			else
				throw new Exception();
	    } catch (Exception e) {
	        String errorMessage = "An error occurred while retrieving the User List.";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}
	
	@PostMapping("/login-user")
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
