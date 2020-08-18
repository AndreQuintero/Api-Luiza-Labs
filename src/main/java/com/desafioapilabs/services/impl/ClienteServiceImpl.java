package com.desafioapilabs.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import com.desafioapilabs.dtos.ClienteDTO;
import com.desafioapilabs.entitys.Cliente;
import com.desafioapilabs.exceptions.BusinessException;
import com.desafioapilabs.exceptions.Exceptions;
import com.desafioapilabs.repositories.ClienteRepository;
import com.desafioapilabs.services.ClienteService;
import com.desafioapilabs.services.ServiceInterface;


@Service
public class ClienteServiceImpl implements ServiceInterface<ClienteDTO>,
	ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void createOrUpdate(ClienteDTO clienteDTO) {
		this.camposClienteObrigatorios(clienteDTO.getNome(), clienteDTO.getEmail());
		this.validateEmail(clienteDTO);
		Cliente cliente = new Cliente();
		cliente.setId(clienteDTO.getId());
		cliente.setNome(clienteDTO.getNome());
		cliente.setEmail(clienteDTO.getEmail());
		this.clienteRepository.save(cliente);	
	}

	@Override
	public List<ClienteDTO> read() {
		List<ClienteDTO> dto = new ArrayList<ClienteDTO>();
		List<Cliente> clientes = this.clienteRepository.findAll();
		for(Cliente c : clientes) {
			dto.add(new ClienteDTO(c.getId(), c.getNome(), c.getEmail()));
		}
		
		return dto;
	}

	@Override
	public ClienteDTO readById(String id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		if(cliente != null && cliente.isPresent()) {
			return new ClienteDTO(cliente.get().getId(),cliente.get().getNome(), cliente.get().getEmail());
		}
		return null;
	}

	@Override
	public void delete(String id) {
	
		ClienteDTO cliente = this.readById(id);
		if(cliente == null) {
			throw new BusinessException(Exceptions.CLIENTE_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
		}
		this.clienteRepository.deleteById(id);
		
	}


	@Override
	public void camposClienteObrigatorios(String nome, String email) {
		
		if(nome == null || email == null) {
			throw new BusinessException(Exceptions.OBRIGATORIO_NOME_EMAIL, HttpStatus.BAD_REQUEST);
		}
		
	}

	private void validateEmail(ClienteDTO cliente) {
		Cliente clientePersisted = this.clienteRepository.findByEmail(cliente.getEmail());
		if(clientePersisted != null && !clientePersisted.getId().equals(cliente.getId())) {
			throw new BusinessException(Exceptions.EMAIL_JA_CADASTRADO, HttpStatus.BAD_REQUEST);
		}
	}

}
