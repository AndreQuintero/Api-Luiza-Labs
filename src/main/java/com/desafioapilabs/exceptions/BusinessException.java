package com.desafioapilabs.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{


	private static final long serialVersionUID = -3801470131036477162L;
    private String mensagem;
    private HttpStatus httpStatus;

    public BusinessException(String mensagem, HttpStatus httpStatus) {
        this.mensagem = mensagem;
        this.httpStatus = httpStatus;
    }

}
