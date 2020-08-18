package com.desafioapilabs.services;

import java.util.List;

public interface ServiceInterface<DTO> {

	public void createOrUpdate(DTO dto);
	public List<DTO> read();
	public DTO readById(String id);
	public void delete(String id);
}
