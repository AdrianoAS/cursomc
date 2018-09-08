package com.adrianoSantos.curosmc.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adrianoSantos.curosmc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer>  {

}
