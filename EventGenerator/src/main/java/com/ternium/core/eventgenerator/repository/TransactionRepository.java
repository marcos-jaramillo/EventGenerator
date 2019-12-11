package com.ternium.core.eventgenerator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ternium.core.eventgenerator.domain.Transaction;
import com.ternium.core.eventgenerator.domain.TransactionPK;
import com.ternium.core.eventgenerator.domain.Transfer;
import com.ternium.core.eventgenerator.domain.TransferPk;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, TransactionPK>{
	
}
