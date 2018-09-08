package com.adrianoSantos.curosmc.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adrianoSantos.curosmc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
