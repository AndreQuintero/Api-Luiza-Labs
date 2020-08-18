package com.desafioapilabs.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.desafioapilabs.entitys.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {
	Cliente findByEmail(String email);
}
