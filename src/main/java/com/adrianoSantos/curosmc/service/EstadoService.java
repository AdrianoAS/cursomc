package com.adrianoSantos.curosmc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianoSantos.curosmc.domain.Estado;
import com.adrianoSantos.curosmc.repositorys.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository service;
	
	public List<Estado>	find(){
		return service.findAllByOrderByNome();
	}
}
