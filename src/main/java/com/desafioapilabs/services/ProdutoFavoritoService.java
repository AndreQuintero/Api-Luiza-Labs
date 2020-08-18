package com.desafioapilabs.services;

import java.util.List;


import com.desafioapilabs.dtos.ProdutoDTO;
import com.desafioapilabs.dtos.ResponseProdutoFavoritoDTO;


public interface ProdutoFavoritoService {
	void produtoExiste(Integer total);
	void camposObrigatorios(String produtoId, String clienteId);
	List<ProdutoDTO> findByClienteId(String clienteId);
	List<ResponseProdutoFavoritoDTO> findDTOByClienteId(String clienteId);
	ResponseProdutoFavoritoDTO findByProdutoFavoritoId(String id);
}
