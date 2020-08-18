package com.desafioapilabs.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseProdutoFavoritoDTO {
	private String id;
	private String cliente;
	private ProdutoDTO produto;
}
