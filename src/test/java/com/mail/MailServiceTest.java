package com.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		mail.setMessage("Hii");
		mail.setSubject("Greetings");
		
		mailServ.saveMail(null, 0, mail);
		
	}
}
