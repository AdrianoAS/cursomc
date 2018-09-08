package com.adrianoSantos.curosmc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianoSantos.curosmc.domain.Cliente;
import com.adrianoSantos.curosmc.exception.ObjectNotFoundException;
import com.adrianoSantos.curosmc.repositorys.ClienteRepository;

@Service
public class ClienteService {

	@Autowired ClienteRepository repo;
	
	public Cliente buscarPorId(Integer id) {
		Optional<Cliente> cli = repo.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + id + ",Tipo :" + Cliente	.class.getName()));
	}
}
