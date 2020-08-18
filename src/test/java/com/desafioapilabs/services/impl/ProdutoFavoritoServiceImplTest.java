package com.desafioapilabs.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import org.springframework.boot.test.context.SpringBootTest;

import com.desafioapilabs.dtos.ClienteDTO;
import com.desafioapilabs.dtos.ProdutoDTO;
import com.desafioapilabs.dtos.ProdutoFavoritoDTO;
import com.desafioapilabs.dtos.ResponseProdutoFavoritoDTO;
import com.desafioapilabs.entitys.ProdutoFavorito;
import com.desafioapilabs.exceptions.BusinessException;
import com.desafioapilabs.repositories.ProdutoFavoritoRepository;

@SpringBootTest
public class ProdutoFavoritoServiceImplTest {

	private static String ID = "5";
	@Mock
	private ProdutoServiceImpl produtoService;
	
	@Mock
	private ClienteServiceImpl clienteService;
	
	
	@Mock
	ProdutoFavoritoRepository produtoRepository;
	
	@InjectMocks
	@Spy
	ProdutoFavoritoServiceImpl produtoFavoritoService;
	
	@Test
	public void createOrUpdateRequiredFieldsTest() {
		ProdutoFavoritoDTO favorito = new ProdutoFavoritoDTO();
		favorito.setClienteId(null);
		favorito.setProdutoId("1");
		
		assertThrows(BusinessException.class, () -> {
			this.produtoFavoritoService.createOrUpdate(favorito);
		});
	}
	
	@Test
	public void produtoJaExisteCreateUpdateTest() {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setId("1");
		
		ClienteDTO cliente = new ClienteDTO();
		cliente.setId("1");
		
		ProdutoFavoritoDTO dto= new ProdutoFavoritoDTO();
		dto.setClienteId("1");
		dto.setProdutoId("1");
		
		when(this.produtoService.getProdutoById("1"))
		.thenReturn(produto);
		
		when(this.clienteService.readById("1"))
		.thenReturn(cliente);
		
		when(this.produtoRepository.
				countByClienteIdAndProdutoFavoritoId(cliente.getId(), produto.getId()))
		.thenReturn(1);
		
		assertThrows(BusinessException.class, () -> {
			this.produtoFavoritoService.createOrUpdate(dto);
		});
	}
	
	@Test
	public void createOrUpdateTest() {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setId("1");
		
		ClienteDTO cliente = new ClienteDTO();
		cliente.setId("1");
		
		ProdutoFavoritoDTO dto = new ProdutoFavoritoDTO();
		dto.setClienteId("1");
		dto.setProdutoId("1");
		
		when(this.produtoService.getProdutoById("1"))
		.thenReturn(produto);
		
		when(this.clienteService.readById("1"))
		.thenReturn(cliente);
		
		when(this.produtoRepository.
				countByClienteIdAndProdutoFavoritoId(cliente.getId(), produto.getId()))
		.thenReturn(0);
		this.produtoFavoritoService.createOrUpdate(dto);
	}
	
	@Test
	public void findByProdutoFavoritoIdTest() {
		
		ProdutoFavorito favorito = this.getFavorito();
		
		Optional<ProdutoFavorito> optionalFavorito =  Optional.of(favorito);
		
		when(this.produtoRepository.findById(ID))
		.thenReturn(optionalFavorito);
		
		assertEquals("5", this.produtoFavoritoService.findByProdutoFavoritoId(ID).getCliente());
		
	}
	
	@Test
	public void deleteFailTest() {
		when(this.produtoFavoritoService.findByProdutoFavoritoId(ID))
		.thenReturn(null);
		
		assertThrows(BusinessException.class, () -> {
			this.produtoFavoritoService.delete(ID);
		});
	}
	
	@Test
	public void deleteTest() {
		ProdutoFavorito favorito = this.getFavorito();
		ResponseProdutoFavoritoDTO dto = new ResponseProdutoFavoritoDTO();
		dto.setId(favorito.getId());
		dto.setCliente(favorito.getClienteId());
		dto.setProduto(favorito.getProdutoFavorito());
		
		this.clienteService.delete(ID);
	}
	
	
	@Test
	public void produtoExiste() {
		Integer total = 1;
		
		assertThrows(BusinessException.class, () -> {
			this.produtoFavoritoService.produtoExiste(total);
		});
	}
	
	@Test
	public void findDTOByClienteIdTest() {
		when(this.produtoRepository.findByClienteId(ID))
		.thenReturn(Stream.of(this.getFavorito()));
		
		assertEquals(1, this.produtoFavoritoService.findDTOByClienteId(ID).size());
	}
	
	private ProdutoFavorito getFavorito() {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setId("1");
		produto.setImage("image.jpg");
		produto.setName("Fogão");
		produto.setPrice(new BigDecimal(500.99));
		
		ProdutoFavorito favorito = new ProdutoFavorito();
		favorito.setClienteId("5");
		favorito.setId("1");
		favorito.setProdutoFavorito(produto);
		return favorito;
	}
}
