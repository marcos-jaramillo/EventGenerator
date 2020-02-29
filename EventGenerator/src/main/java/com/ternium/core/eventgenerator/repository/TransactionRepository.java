package com.ternium.core.eventgenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ternium.core.eventgenerator.domain.Transaction;
import com.ternium.core.eventgenerator.domain.TransactionPK;
/**
 * 
 * Interfaz que nos proporciona acceso a las operaciones sobre la coleccion Transactions por medio de MongoTemplate
 *
 */
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, TransactionPK>{
	
}
