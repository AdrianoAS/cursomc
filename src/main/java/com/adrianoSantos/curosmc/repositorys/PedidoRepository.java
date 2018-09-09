package com.adrianoSantos.curosmc.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adrianoSantos.curosmc.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

}
