package com.adrianoSantos.curosmc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianoSantos.curosmc.domain.Cidade;
import com.adrianoSantos.curosmc.repositorys.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> findByEstado(Integer estadoId){
		return cidadeRepository.findByCidade(estadoId);
	}
}
