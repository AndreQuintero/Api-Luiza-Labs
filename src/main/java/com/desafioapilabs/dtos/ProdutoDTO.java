package com.desafioapilabs.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoDTO {

	private String id;
	
	private String name;
	
	private BigDecimal price;
	
	private String image;
}
