package com.desafioapilabs.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;


import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.desafioapilabs.dtos.ProdutoDTO;
import com.desafioapilabs.exceptions.BusinessException;

@SpringBootTest
public class ProdutoServiceImplTest {
	
	@InjectMocks
	@Spy
	ProdutoServiceImpl produtoService;
	
	@Value("${produtos.base.url}")
	private String BASE_URL;
	
	@Mock
	RestTemplate restTemplate;
	
	@Test
	public void getProdutoByIdTest() {
		String produtoId = "1";
		ProdutoDTO produto = new ProdutoDTO();
		produto.setId("1");
		produto.setImage("teste.jpg");
		produto.setName("Geladeira");
		produto.setPrice(new BigDecimal(500.00));

		ResponseEntity<ProdutoDTO> responseEntity = new ResponseEntity<ProdutoDTO>(produto, HttpStatus.OK);
		
		when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ProdutoDTO>>any()))
             .thenReturn(responseEntity);
		
		ProdutoDTO res = this.produtoService.getProdutoById(produtoId);
        assertEquals("Geladeira", res.getName());
	}
	
	@Test
	public void getProdutoByIdFailTest() {
		
		when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<ProdutoDTO>>any()))
             .thenThrow(HttpClientErrorException.class);
		
		assertThrows(BusinessException.class, () -> {
			this.produtoService.getProdutoById("100000");
		});
	}
}
