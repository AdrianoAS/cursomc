package com.adrianoSantos.curosmc.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.adrianoSantos.curosmc.security.UserSpringSecurity;

@Service
public class UserService {

	public static UserSpringSecurity Authenticated() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		} catch (Exception e) {
			return  null;
		}
		
	}
}
