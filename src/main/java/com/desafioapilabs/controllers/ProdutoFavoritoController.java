package com.desafioapilabs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioapilabs.dtos.ProdutoDTO;
import com.desafioapilabs.dtos.ProdutoFavoritoDTO;
import com.desafioapilabs.dtos.ResponseProdutoFavoritoDTO;
import com.desafioapilabs.services.impl.ProdutoFavoritoServiceImpl;


@RestController
@RequestMapping("/api/produto-favorito")
public class ProdutoFavoritoController {
	
	@Autowired
	private ProdutoFavoritoServiceImpl produtoFavoritoService;
	
	
	@PostMapping
	public ResponseEntity<ProdutoFavoritoDTO> insereProdutoFavorito(@RequestBody ProdutoFavoritoDTO dto){
		
		this.produtoFavoritoService.createOrUpdate(dto);
	 	return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value="/{idCliente}")
	public ResponseEntity<List<ProdutoDTO>> getProdutosFavoritosPorCliente(@PathVariable String idCliente) {
		List<ProdutoDTO> produtosFavoritos =  this.produtoFavoritoService.findByClienteId(idCliente);
		return ResponseEntity.ok(produtosFavoritos);
	}
	
	@GetMapping(value="/dto/{idCliente}")
	public ResponseEntity<List<ResponseProdutoFavoritoDTO>> getProdutosFavoritosDTOPorCliente(@PathVariable String idCliente) {
		List<ResponseProdutoFavoritoDTO> produtosFavoritos =  this.produtoFavoritoService.findDTOByClienteId(idCliente);
		return ResponseEntity.ok(produtosFavoritos);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> deleteProdutoFavorito(@PathVariable String id) {
		this.produtoFavoritoService.delete(id);
		return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
