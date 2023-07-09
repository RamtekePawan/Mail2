package com.mail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mail.entity.Mail;
import com.mail.entity.User;

public interface MailRepository extends JpaRepository<Mail, Integer>{

	@Query("SELECT m from Mail m JOIN m.user u WHERE u.userId = ?1 AND m.type= 'RECIEVED' ORDER BY m.dateAndTime DESC ")
	//@Query("SELECT m from User m WHERE m.userId = ?1 ")
	public List<Mail> findByUserRecieved(int userId);
	
	//public List<Mail> findByUser();
};

