package com.adrianoSantos.curosmc.service;

import org.springframework.mail.SimpleMailMessage;

import com.adrianoSantos.curosmc.domain.Pedido;

public interface EmailService {

	void sendOderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
