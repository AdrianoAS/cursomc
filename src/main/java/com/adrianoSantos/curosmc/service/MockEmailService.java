package com.adrianoSantos.curosmc.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;


public class MockEmailService extends AbstractEmailService {

	private static final Logger log = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		log.info("Simulando um envio de Email");
		log.info(msg.toString());
		log.info("Email Enviado");
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		log.info("Simulando um envio de Email HTML...");
		log.info(msg.toString());
		log.info("Email Enviado");
		
	}

}
