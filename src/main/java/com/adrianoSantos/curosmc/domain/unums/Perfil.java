package com.adrianoSantos.curosmc.domain.unums;

public enum Perfil {
	CLIENTE(1,"ROLE_CLIENTE"),
	ADMIN(2,"ROLE_ADMIN");
	
	private int cod;
	private String descricao;
	
	public int getCod() {
		return cod;
	}
	
	
	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}


	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(Perfil x : Perfil.values()){
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id invalido" + cod);
	}
}
