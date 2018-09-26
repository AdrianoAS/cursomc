package com.adrianoSantos.curosmc.dto;

import java.io.Serializable;

import com.adrianoSantos.curosmc.domain.Estado;

public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
		
	public EstadoDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}
	
	public EstadoDTO(Estado obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
