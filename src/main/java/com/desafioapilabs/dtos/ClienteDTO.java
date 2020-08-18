package com.desafioapilabs.dtos;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {
	
	// Por mais que seja igual a classe Cliente (Entity) resolvi separar um DTO caso hipoteticamente houvessem dados que não deveriam ser passados

	@Field("id")
	private String id;
	
	@Field("nome")
	private String nome;
	
	@Field("email")
	private String email;
	
}
