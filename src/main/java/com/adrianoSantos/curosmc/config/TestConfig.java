package com.adrianoSantos.curosmc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.adrianoSantos.curosmc.service.BDService;
import com.adrianoSantos.curosmc.service.EmailService;
import com.adrianoSantos.curosmc.service.MockEmailService;

@Configuration
@Profile("teste")
public class TestConfig {
	
	@Autowired
	private BDService dbservice;
	
	@Bean
	public boolean inistantiateDataBase() throws ParseException {
		dbservice.instantiateTesteDataBase();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
