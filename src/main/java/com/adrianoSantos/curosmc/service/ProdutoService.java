package com.adrianoSantos.curosmc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.adrianoSantos.curosmc.domain.Categoria;
import com.adrianoSantos.curosmc.domain.Produto;
import com.adrianoSantos.curosmc.exception.ObjectNotFoundException;
import com.adrianoSantos.curosmc.repositorys.CategoriaRepository;
import com.adrianoSantos.curosmc.repositorys.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private  ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository cagetogoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não encontrado" + id + ", Tipo:" + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page,Integer linesPages, String orderBy,String direction){
			PageRequest pageResquest = new PageRequest(page, linesPages,Direction.valueOf(direction),orderBy );
			List<Categoria> categorias = cagetogoriaRepository.findAllById(ids);
			return produtoRepository.search(nome, categorias, pageResquest);
	}
}
