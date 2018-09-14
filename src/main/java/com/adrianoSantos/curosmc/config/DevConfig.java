package com.adrianoSantos.curosmc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.adrianoSantos.curosmc.service.BDService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private BDService dbservice;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean inistantiateDataBase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		dbservice.instantiateTesteDataBase();
		return true;
	}
}
