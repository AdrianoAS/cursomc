package com.adrianoSantos.curosmc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.adrianoSantos.curosmc.domain.Cliente;
import com.adrianoSantos.curosmc.domain.Cliente;
import com.adrianoSantos.curosmc.dto.ClienteDTO;
import com.adrianoSantos.curosmc.exception.DataIntegrityException;
import com.adrianoSantos.curosmc.exception.ObjectNotFoundException;
import com.adrianoSantos.curosmc.repositorys.ClienteRepository;

@Service
public class ClienteService {

	@Autowired ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cli = repo.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + id + ",Tipo :" + Cliente	.class.getName()));
	}
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public  Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque ha entidades relacionadas");
		}
		
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page,Integer linesPages,String orderBy,String direction){
		PageRequest pageResquest = PageRequest.of(page, linesPages,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageResquest);
	}
	public Cliente fromDTO(ClienteDTO ObjDto) {
		return new Cliente(ObjDto.getId(),ObjDto.getNome(), ObjDto.getEmail(), null,null);
	}
	
	private void updateData(Cliente newObj ,Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
