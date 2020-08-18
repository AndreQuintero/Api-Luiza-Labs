package com.desafioapilabs.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.desafioapilabs.dtos.ClienteDTO;
import com.desafioapilabs.services.UtilsService;
import com.desafioapilabs.services.impl.ClienteServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cliente")
@Tag(name="cliente", description = "Cliente Controller")
public class ClienteController {
	
	@Autowired
	private ClienteServiceImpl clienteService;
	
	@Autowired
	private UtilsService utilsService;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getAllClientes() {
		List<ClienteDTO> dto = this.clienteService.read();
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClienteDTO> getByClienteId(@PathVariable String id){
		ClienteDTO dto = this.clienteService.readById(id);
		// TODO: DAR NOT-FOUND
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<?> insertCliente(@RequestBody ClienteDTO cliente) {
		this.utilsService.idIsNull(cliente.getId(), true);
		this.clienteService.createOrUpdate(cliente);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable String id) {
		this.clienteService.delete(id);
		return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<?> updateCliente(@RequestBody ClienteDTO cliente) {
		this.utilsService.idIsNull(cliente.getId(), false);
		this.clienteService.createOrUpdate(cliente);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
