package com.mail.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_mail2")
public class Mail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mailId;
	
	private String message;// req.body.message
	private String subject;
	
	private LocalDateTime dateAndTime;
	
	private String type;  //send /recieved
    private boolean isImportant;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	public int getMailId() {
		return mailId;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public boolean isImportant() {
		return isImportant;
	}


	public void setImportant(boolean isImportant) {
		this.isImportant = isImportant;
	}
}

