package com.adrianoSantos.curosmc.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO {
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message= "Email Invalido")
	private String email;
	
	public EmailDTO() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
