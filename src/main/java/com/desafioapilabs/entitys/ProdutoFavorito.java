package com.desafioapilabs.entitys;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.desafioapilabs.dtos.ProdutoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoFavorito {
	@Id
	private String id;
	
	private String clienteId;
	private ProdutoDTO produtoFavorito;
}
