package com.adrianoSantos.curosmc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.adrianoSantos.curosmc.domain.Cliente;
import com.adrianoSantos.curosmc.domain.unums.TipoCliente;
import com.adrianoSantos.curosmc.dto.ClienteNewDTO;
import com.adrianoSantos.curosmc.repositorys.ClienteRepository;
import com.adrianoSantos.curosmc.resources.exception.FieldMessage;
import com.adrianoSantos.curosmc.service.validation.utils.BR;

public class ClienteInsertValidation implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	
	@Override
	public boolean isValid(ClienteNewDTO ObjDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(ObjDto.getTipo().equals(TipoCliente.PessoaFisica.getCod()) && !BR.isValidCPF(ObjDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
		}
		if(ObjDto.getTipo().equals(TipoCliente.PessoaJuridica.getCod()) && !BR.isValidCNPJ(ObjDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		Cliente aux = repo.findByEmail(ObjDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email","Email já existente"));
		}
		
		
		//incluir os testes aqui, inserindo erros de lista
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
