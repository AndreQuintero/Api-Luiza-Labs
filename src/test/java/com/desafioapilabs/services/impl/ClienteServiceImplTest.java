package com.desafioapilabs.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import com.desafioapilabs.dtos.ClienteDTO;
import com.desafioapilabs.entitys.Cliente;
import com.desafioapilabs.exceptions.BusinessException;
import com.desafioapilabs.repositories.ClienteRepository;

@SpringBootTest
public class ClienteServiceImplTest {

	@InjectMocks
	@Spy
	ClienteServiceImpl clienteService;
	
	@Mock
	ClienteRepository clienteRepository;
	
	@Test
	public void createOrUpdateTest() {
		ClienteDTO dto = this.getClienteDTO();	
		when(this.clienteRepository.findByEmail(dto.getEmail())).thenReturn(null);
		this.clienteService.createOrUpdate(dto);
		assertTrue(true);
	}
	
	@Test
	public void createOrUpdateRequiredFieldsTest() {
		ClienteDTO dto = new ClienteDTO();
		dto.setNome("André");
		dto.setEmail(null);
		assertThrows(BusinessException.class, () -> {
			this.clienteService.createOrUpdate(dto);
		});
	
	}
	
	@Test
	public void createOrUpdateEmailUpdateFailedTest() {
		ClienteDTO dto = new ClienteDTO();
		dto.setId("2");
		dto.setNome("André");
		dto.setEmail("andre@teste.com");
		
		
		when(this.clienteRepository.findByEmail(dto.getEmail()))
		.thenReturn(this.getCliente());
		
		assertThrows(BusinessException.class, () -> {
			this.clienteService.createOrUpdate(dto);
		});
	}
	
	@Test
	public void createOrUpdateEmailUpdateTest() {
		ClienteDTO dto = new ClienteDTO();
		dto.setId("1");
		dto.setNome("André");
		dto.setEmail("andre2@teste.com");
		
		
		when(this.clienteRepository.findByEmail(dto.getEmail()))
		.thenReturn(this.getCliente());
		this.clienteService.createOrUpdate(dto);
		
	}
		
	@Test
	public void readTest() {
		List<Cliente> list= new ArrayList<Cliente>();
		list.add(new Cliente("1","André", "andre@teste.com"));
		when(this.clienteRepository.findAll()).thenReturn(list);
		assertEquals(list.size(), this.clienteService.read().size());
	}
	
	
	@Test
	public void readByIdNullTest() {
		when(this.clienteRepository.findById("1212")).thenReturn(null);
		assertEquals(null, this.clienteService.readById("1212"));
	}
	
	@Test
	public void readByIdTest() {

		ClienteDTO dto = new ClienteDTO();
		dto.setId("1");
		dto.setNome("André");
		dto.setEmail("email@teste.com");
		
		Optional<Cliente> cliente = null;
		when(this.clienteRepository.findById("1"))
		.thenReturn(cliente.of(this.getCliente()));
		
		assertEquals(dto.getId(), this.clienteService.readById("1").getId());
	}
	
	
	@Test
	public void deleteByIdTest() {
		when(this.clienteRepository.findById("1")).thenReturn(Optional.of(this.getCliente()));
		this.clienteService.delete("1");
		assertTrue(true);
	}
	
	@Test
	public void deleteByIdFailTest() {
		String id = "1";
		when(this.clienteRepository.findById(id)).thenReturn(null);
		assertThrows(BusinessException.class, () -> {
			this.clienteService.delete(id);
		});
	}
	
	private ClienteDTO getClienteDTO() {
		
		ClienteDTO dto = new ClienteDTO();
		dto.setNome("André");
		dto.setEmail("email@teste.com");
		return dto;
	}
	
	private Cliente getCliente() {
		Cliente cliente = new Cliente();
		cliente.setId("1");
		cliente.setEmail("andre@teste.com");
		cliente.setNome("André");
		return cliente;
	}
}
