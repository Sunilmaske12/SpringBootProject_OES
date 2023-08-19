package com.springBoot.jsp.OES.service;

public interface GmailSenderService {

	void sendMail(String to, String from, String subject, String text);

}
