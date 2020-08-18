package com.desafioapilabs.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.desafioapilabs.dtos.ClienteDTO;
import com.desafioapilabs.dtos.ProdutoDTO;
import com.desafioapilabs.dtos.ProdutoFavoritoDTO;
import com.desafioapilabs.dtos.ResponseProdutoFavoritoDTO;
import com.desafioapilabs.entitys.ProdutoFavorito;
import com.desafioapilabs.exceptions.BusinessException;
import com.desafioapilabs.exceptions.Exceptions;
import com.desafioapilabs.repositories.ProdutoFavoritoRepository;
import com.desafioapilabs.services.ProdutoFavoritoService;
import com.desafioapilabs.services.ServiceInterface;

@Service
public class ProdutoFavoritoServiceImpl implements ServiceInterface<ProdutoFavoritoDTO>, 
	ProdutoFavoritoService {

	@Autowired
	private ProdutoServiceImpl produtoService;
	
	@Autowired
	private ClienteServiceImpl clienteService;
	
	
	@Autowired
	ProdutoFavoritoRepository produtoRepository;
	
	@Override
	public void createOrUpdate(ProdutoFavoritoDTO dto) {
		this.camposObrigatorios(dto.getProdutoId(), dto.getClienteId());
		ProdutoDTO produto = this.produtoService.getProdutoById(dto.getProdutoId());
		ClienteDTO cliente = this.clienteService.readById(dto.getClienteId());
	
		Integer qtdProdutosExistentes = this.produtoRepository.countByClienteIdAndProdutoFavoritoId(cliente.getId(), produto.getId());
		this.produtoExiste(qtdProdutosExistentes);
		
		ProdutoFavorito favorito = new ProdutoFavorito();
		favorito.setClienteId(cliente.getId());
		favorito.setProdutoFavorito(produto);
		this.produtoRepository.save(favorito);
	}

	@Override
	public List<ProdutoFavoritoDTO> read() {
		// Não há implementação nesta classe
		return null;
	}

	@Override
	public ProdutoFavoritoDTO readById(String id) {
		// Não há implementação nesta classe
		return null;
	}

	@Override
	public void delete(String id) {
		ResponseProdutoFavoritoDTO dto = this.findByProdutoFavoritoId(id);
		if(dto == null) {
			throw new BusinessException(Exceptions.PRODUTO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
		}
		this.produtoRepository.deleteById(id);
		
	}


	

	@Override
	public void camposObrigatorios(String produtoId, String clienteId) {
		if(produtoId == null || clienteId == null) {
				throw new BusinessException(Exceptions.ID_PRODUTO_E_CLIENTE_OBRIGATORIOS, HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public List<ProdutoDTO> findByClienteId(String clienteId) {
		Stream<ProdutoFavorito> favoritos = this.produtoRepository.findByClienteId(clienteId);
		return this.getProdutoDTO(favoritos).collect(Collectors.toList());
	}

	private Stream<ProdutoDTO> getProdutoDTO(Stream<ProdutoFavorito> favoritos){
			return favoritos.
					map( produtoFav -> produtoFav.getProdutoFavorito() );
	}

	@Override
	public ResponseProdutoFavoritoDTO findByProdutoFavoritoId(String id) {
		Optional<ProdutoFavorito> favorito = this.produtoRepository.findById(id);
		if(favorito != null && favorito.isPresent()) {
			ProdutoFavorito produtoFavorito = favorito.get();
			ResponseProdutoFavoritoDTO dto = new ResponseProdutoFavoritoDTO();
			dto.setId(produtoFavorito.getId());
			dto.setCliente(produtoFavorito.getClienteId());
			dto.setProduto(produtoFavorito.getProdutoFavorito());
			return dto;
		}
		return null;
	}

	@Override
	public List<ResponseProdutoFavoritoDTO> findDTOByClienteId(String clienteId) {
		Stream<ProdutoFavorito> favoritos = this.produtoRepository.findByClienteId(clienteId);
		List<ResponseProdutoFavoritoDTO> responseList = new ArrayList<ResponseProdutoFavoritoDTO>();
		for(ProdutoFavorito produto : favoritos.collect(Collectors.toList())) {
			ResponseProdutoFavoritoDTO resp = new ResponseProdutoFavoritoDTO();
			resp.setId(produto.getId());
			resp.setCliente(produto.getClienteId());
			resp.setProduto(produto.getProdutoFavorito());
			responseList.add(resp);
		}
		return responseList;
	}

	@Override
	public void produtoExiste(Integer total) {
		
		if(total > 0) {
			throw new BusinessException(Exceptions.PRODUTO_JA_ESTA_EM_FAVORITOS, HttpStatus.BAD_REQUEST);
		}
	}
}
