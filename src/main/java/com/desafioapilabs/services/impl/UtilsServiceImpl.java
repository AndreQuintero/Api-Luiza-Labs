package com.desafioapilabs.services.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.desafioapilabs.exceptions.BusinessException;
import com.desafioapilabs.exceptions.Exceptions;
import com.desafioapilabs.services.UtilsService;

@Service
public class UtilsServiceImpl implements UtilsService{
	
	/**
	 * Metodo responsavel por verificar se é obrigatorio o id 
	 * ou não pela payload
	 * @param String id
	 * @param Boolean shouldBeNull
	 */
	@Override
	public void idIsNull(String id, Boolean shouldBeNull) {
		
		if(shouldBeNull) {
			if(id != null) {
				throw new BusinessException(Exceptions.ID_DEVE_SER_NULO, HttpStatus.BAD_REQUEST);
			}
		} else {
			if(id == null) {
				throw new BusinessException(Exceptions.ID_NAO_DEVE_SER_NULO, HttpStatus.BAD_REQUEST);
			}
		}
	}
}
