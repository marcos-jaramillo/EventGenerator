package com.ternium.core.eventgenerator.visitor.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Component;

import com.ternium.core.eventgenerator.domain.Transaction;
import com.ternium.core.eventgenerator.exception.DataAlreadyExistException;
import com.ternium.core.eventgenerator.exception.EventNotFoundException;
import com.ternium.core.eventgenerator.exception.EventRequiredRecordsAmountException;
import com.ternium.core.eventgenerator.exception.RuleNotMatchException;
import com.ternium.core.eventgenerator.messenger.IMessenger;
import com.ternium.core.eventgenerator.messenger.vo.Message;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
import com.ternium.core.eventgenerator.repository.TransactionRepository;
import com.ternium.core.eventgenerator.util.KieServerProperties;
import com.ternium.core.eventgenerator.util.MessageBuilderHelper;
import com.ternium.core.eventgenerator.util.TranslatorUtils;
import com.ternium.core.eventgenerator.visitor.Visitor;
import com.ternium.core.eventgenerator.visitor.element.EventElement;

@Component
public class DataTransformVisitor implements Visitor{
    private static Logger logger = LoggerFactory.getLogger(DataTransformVisitor.class);

    @Autowired
	IMessenger rulesMessenger;
    
    @Autowired
	KieServerProperties kieServerProperties;
    
    @Autowired
	TransactionRepository transactionRepository;
    
    @Autowired
    MongoTemplate mongoTemplate;
    
    @Autowired
    MessageBuilderHelper messageBuilderHelper;
    
    @Value("${kieserver.translatoragendarule}")
	private String translatorAgendaRule;
    
	@Override
	public void visit(EventElement element) throws Exception {
		logger.info("Apply Data Transform to " + element.getMessage());
    	
		MessageVO messageVO = new MessageVO();
		String translatorMap = null;
		Message message = element.getMessageObj();		
		Map dataMap = null;
		Map eventDataMap = null;
		MongoOperations mongoOperation = (MongoOperations) mongoTemplate;
		String resolvedString = null;
		StringSubstitutor sub = null;
		BasicQuery query = null;
		List<Transaction> transactions = null;
		
		Transaction transaction = new Transaction(message.getDomain(), message.getTrx(), message.getTimestamp(), message.getData());
		
		//Obtain translator data
		messageVO.setGroupName(translatorAgendaRule);
		messageVO.setContainer(kieServerProperties.getContainer());
		messageVO.setMessage(element.getMessage());
		messageVO.setMessageObj(message);
		
		rulesMessenger.sendMessage(messageVO);
		
		translatorMap = messageVO.getTranslatorMap();
		if(translatorMap != null && !translatorMap.isEmpty()) {
			//Apply tranlator to the message data object
			dataMap = TranslatorUtils.applyTransalator(message.getData(), translatorMap);
			
			message.setData(dataMap);
			//Overwrite original input string
			element.objToMessage();
		}
		
		messageVO.setGroupName(element.getGroupName());
		messageVO.setContainer(kieServerProperties.getContainer());
		messageVO.setMessage(element.getMessage());
		messageVO.setMessageObj(element.getMessageObj());
		
		rulesMessenger.sendMessage(messageVO);		
		
		element.setCache(messageVO.getCache());
		element.setGroupName(messageVO.getGroupName());
		element.setEvent(messageVO.getEvent());
		
		if(element.getGroupName() == null || element.getGroupName().isEmpty()) {
			throw new RuleNotMatchException("No Rule retrived for " + element.getMessage() + " from rule  " + element.getGroupName());
		}
		
		if(element.getEvent() == null || element.getEvent().isEmpty()) {
			throw new EventNotFoundException("Error while validate the Event for " + element.getMessage() + " from rule  " + element.getGroupName());
		}
		
		element.setEventDataMap(message.getData());
		if(element.getCache()) {
			if(!transactionRepository.findById(transaction.getId()).isPresent()) {
				transactionRepository.save(transaction);
			}else {
				logger.info("Data Already Exist");
				throw new DataAlreadyExistException("Data Already Exist for " + transaction.toString());
			}
		
			if(messageVO.getJsonQuery() != null && !messageVO.getJsonQuery().isEmpty()) {
				sub = new StringSubstitutor(element.getMessageObj().getData());
				resolvedString = sub.replace(messageVO.getJsonQuery());
				
				query = new BasicQuery(resolvedString);
				transactions = mongoOperation.find(query, Transaction.class);
				logger.info("QUERY EXECUTED " + resolvedString + " Records :: " + (transactions!=null?transactions.size():0));
				
				if(transactions == null || transactions.size() < messageVO.getExpectedTrxs()) {
					logger.warn("The number of transactions to build the event is not yet completed. " + "Event " + element.getEvent() + "||Message " + element.getMessage());
					element.setEventDataMap(null);
				}else {
					if(messageVO.getMaster()) {
						eventDataMap = new HashMap();
						for(Transaction cacheTransaction : transactions) {
							eventDataMap.putAll(cacheTransaction.getData());
						}
						element.setEventDataMap(eventDataMap);
					}else {
						for(Transaction cacheTransaction : transactions) {
							messageBuilderHelper.proccesMasterMessage(cacheTransaction, messageVO, element);
							break;
						}
					}
				}
			}else if(messageVO.getMaster()) {
				
				messageBuilderHelper.proccesMasterMessage(transaction, messageVO, element);
			}
		}
	}

}
