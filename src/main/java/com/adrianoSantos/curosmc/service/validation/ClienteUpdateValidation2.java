package com.adrianoSantos.curosmc.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.adrianoSantos.curosmc.domain.Cliente;
import com.adrianoSantos.curosmc.dto.ClienteDTO;
import com.adrianoSantos.curosmc.repositorys.ClienteRepository;
import com.adrianoSantos.curosmc.resources.exception.FieldMessage;

public class ClienteUpdateValidation2 implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	public void initialize(ClienteDTO ann) {
	}
	
	
	@Override
	public boolean isValid(ClienteDTO ObjDto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		Map<String , String > map = (Map<String , String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		Cliente aux = repo.findByEmail(ObjDto.getEmail());
		if(aux != null && !aux.getId().equals(uriId)) {
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
