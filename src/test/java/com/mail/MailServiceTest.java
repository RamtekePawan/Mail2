package com.mail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.event.TransactionalEventListener;

import com.mail.entity.Mail;
import com.mail.repository.MailRepository;
import com.mail.service.MailService;

@SpringBootTest
public class MailServiceTest {

	@Autowired
	private MailRepository mailRepo;
	
	@Autowired
	private MailService mailServ;
	
	@Test
	public void saveMail_test() {
		
		Mail mail = new Mail();
		mail.setMessage("I an Pawan !!!");
		mail.setSubject("Introduction");
		
		mailServ.saveMail("pawan@gmail.com", 2, mail);
		
	}
	
	@Test
	public void findMailRecieved_test() {
		List<Mail> list = null;
		list = mailServ.getMails(1);
		System.out.println("---------------------------------");
		for (Mail mail : list) {
			System.out.println(mail.getMessage());
		}
		System.out.println("---------------------------------");
	}
}
