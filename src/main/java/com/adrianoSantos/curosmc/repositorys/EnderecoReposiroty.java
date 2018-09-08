package com.adrianoSantos.curosmc.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adrianoSantos.curosmc.domain.Endereco;

@Repository
public interface EnderecoReposiroty extends JpaRepository<Endereco, Integer> {

}
