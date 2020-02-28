package com.ternium.core.eventgenerator.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Component;

import com.ternium.core.eventgenerator.domain.Transaction;
import com.ternium.core.eventgenerator.exception.EventRequiredRecordsAmountException;
import com.ternium.core.eventgenerator.exception.MapGenerationException;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
import com.ternium.core.eventgenerator.visitor.element.EventElement;

@Component
public class MessageBuilderHelper {
	private static Logger logger = LoggerFactory.getLogger(MessageBuilderHelper.class);
	
	@Autowired
    MongoTemplate mongoTemplate;

	public void proccesMasterMessage(Transaction transaction, MessageVO messageVO, EventElement element, List<Transaction> processedTransaction) throws MapGenerationException, EventRequiredRecordsAmountException{
		Object elementChildObj = transaction.getData().get(messageVO.getTagChild());
		Iterator<Object> itElements = null;
		Map<Object,Object> elementMap;
		MongoOperations mongoOperation = (MongoOperations) mongoTemplate;
		String resolvedString = null;
		StringSubstitutor sub = null;
		BasicQuery query = null;
		List<Transaction> transactions = null;
		
		List<Object> lstElements = (List<Object>)elementChildObj;
		
		if(lstElements != null) {
			processedTransaction.add(transaction);
			
			itElements = lstElements.iterator();

			while(itElements.hasNext()) {
				elementMap = (Map)itElements.next();
				
				Map keysMap = MapUtils.createKeyMapFronJsonQuery(messageVO.getJsonQueryChild());
				
				MapUtils.obtainKeysFromMap(keysMap, (Map)elementMap);
				
				sub = new StringSubstitutor(keysMap);
				resolvedString = sub.replace(messageVO.getJsonQueryChild());
				
				query = new BasicQuery(resolvedString);
				transactions = mongoOperation.find(query, Transaction.class);
				//logger.info("QUERY EXECUTED " + resolvedString + " Records :: " + (transactions!=null?transactions.size():0));
				
				if(transactions == null || transactions.isEmpty()) {
					element.setEventDataMap(null);
					throw new EventRequiredRecordsAmountException("The number of transactions to build the event is not yet completed. " + "Event " + element.getEvent());
				}else {
					for(Transaction cacheTransaction : transactions) {
						elementMap.putAll(cacheTransaction.getData());
						processedTransaction.add(cacheTransaction);
					}
					element.setEventDataMap(transaction.getData());
				}
			}
		}
	}
}
