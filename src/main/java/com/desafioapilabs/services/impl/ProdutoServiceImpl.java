package com.desafioapilabs.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.desafioapilabs.dtos.ProdutoDTO;
import com.desafioapilabs.exceptions.BusinessException;
import com.desafioapilabs.exceptions.Exceptions;
import com.desafioapilabs.services.ProdutoInterface;

@Service
public class ProdutoServiceImpl implements ProdutoInterface{

	@Value("${produtos.base.url}")
	private String BASE_URL;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Override
	public ProdutoDTO getProdutoById(String id) {
		
		ResponseEntity<ProdutoDTO> entity = null;
		try {
			 entity = restTemplate.exchange(BASE_URL + id, HttpMethod.GET, this.getHttpEntity(), ProdutoDTO.class);
		} catch(HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
			if(HttpStatus.NOT_FOUND.equals(httpClientOrServerExc.getStatusCode())) {
				throw new BusinessException(Exceptions.PRODUTO_NAO_ENCONTRADO, httpClientOrServerExc.getStatusCode());
			}
			throw new BusinessException(Exceptions.ERRO_AO_BUSCAR_PRODUTO, httpClientOrServerExc.getStatusCode());
		}

		return entity.getBody();
	}
	
	private HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		return new HttpEntity<String>(headers);
	}
}
