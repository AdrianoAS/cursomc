package com.adrianoSantos.curosmc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianoSantos.curosmc.domain.Pedido;
import com.adrianoSantos.curosmc.exception.ObjectNotFoundException;
import com.adrianoSantos.curosmc.repositorys.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscarPorId(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado" + id + ", Tipo:" + Pedido.class.getName()));
	}
}
