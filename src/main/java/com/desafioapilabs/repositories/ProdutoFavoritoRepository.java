package com.desafioapilabs.repositories;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.desafioapilabs.entitys.ProdutoFavorito;

@Repository
public interface ProdutoFavoritoRepository extends MongoRepository<ProdutoFavorito, String>{
	Stream<ProdutoFavorito> findByClienteId(String clienteId);
	Integer countByClienteIdAndProdutoFavoritoId(String clienteId, String produtoId);
}
