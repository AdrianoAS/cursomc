package com.adrianoSantos.curosmc.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {
	private static final Logger log = LoggerFactory.getLogger(MockEmailService.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		// TODO Auto-generated method stub
		log.info("Simulando um envio de Email");
		mailSender.send(msg);
		log.info("Email Enviado");
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		log.info("Enviando Email");
		javaMailSender.send(msg);
		log.info("Email Enviado");
		
	}

}
