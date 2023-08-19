package com.springBoot.jsp.OES.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class GmailSenderServiceImpl implements GmailSenderService {

	@Override
	public void sendMail(String to, String from, String subject, String text) {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		
		String Password="colhtgsnvsmaigpf";
		String Username="onlineelectricalshopee@gmail.com";
		
		Session session=Session.getInstance(props, new Authenticator() {
			@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					
					return new PasswordAuthentication(Username, Password);
																									
				}
			});
			
			try {
				Message message=new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject(subject);
				message.setText(text);
				Transport.send(message);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		
	}

}
