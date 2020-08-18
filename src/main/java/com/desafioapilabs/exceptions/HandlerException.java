package com.desafioapilabs.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafioapilabs.dtos.ErrorMessageDTO;

@ControllerAdvice(basePackages = "com.desafioapilabs")
public class HandlerException {

	@ExceptionHandler(BusinessException.class)
    ResponseEntity<ErrorMessageDTO> handleBusinessException(BusinessException businessException) {
        HttpStatus status = businessException.getHttpStatus();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(new ErrorMessageDTO(businessException.getMensagem()), headers, status);

    }
}
