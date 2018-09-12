package com.adrianoSantos.curosmc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrianoSantos.curosmc.domain.Cidade;
import com.adrianoSantos.curosmc.domain.Cliente;
import com.adrianoSantos.curosmc.domain.Endereco;
import com.adrianoSantos.curosmc.domain.unums.TipoCliente;
import com.adrianoSantos.curosmc.dto.ClienteDTO;
import com.adrianoSantos.curosmc.dto.ClienteNewDTO;
import com.adrianoSantos.curosmc.exception.DataIntegrityException;
import com.adrianoSantos.curosmc.exception.ObjectNotFoundException;
import com.adrianoSantos.curosmc.repositorys.ClienteRepository;
import com.adrianoSantos.curosmc.repositorys.EnderecoReposiroty;

@Service
public class ClienteService {

	@Autowired ClienteRepository repo;
	
	@Autowired
	private EnderecoReposiroty enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cli = repo.findById(id);
		return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + id + ",Tipo :" + Cliente	.class.getName()));
	}
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(),null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento() ,objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() !=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj ,Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
