package com.ternium.core.eventgenerator.service;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ternium.core.eventgenerator.enums.ProcessCodeEnum;
import com.ternium.core.eventgenerator.exception.DataAlreadyExistException;
import com.ternium.core.eventgenerator.exception.EventNotFoundException;
import com.ternium.core.eventgenerator.exception.EventRequiredRecordsAmountException;
import com.ternium.core.eventgenerator.exception.InvalidDataFormatException;
import com.ternium.core.eventgenerator.exception.InvalidEnrichmentDataException;
import com.ternium.core.eventgenerator.exception.MainRuleNotMatchException;
import com.ternium.core.eventgenerator.exception.MapGenerationException;
import com.ternium.core.eventgenerator.exception.RuleMessengerException;
import com.ternium.core.eventgenerator.exception.RuleNotMatchException;
import com.ternium.core.eventgenerator.exception.TopicNotMatchException;
import com.ternium.core.eventgenerator.visitor.Element;
import com.ternium.core.eventgenerator.visitor.Visitor;
import com.ternium.core.eventgenerator.visitor.element.EventElement;

@Service
public class EventGeneratorService {
	private static Logger logger = LoggerFactory.getLogger(EventGeneratorService.class); 
	
	@Resource
	Visitor filterVisitor;
	
	@Resource
	Visitor dataTransformVisitor;
	
	@Resource
	Visitor messageBuilderVisitor;
	
	public void processMessage(String message, String messageKey, long startMillis) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			try {
				Element element = new EventElement(message);
				MDC.put("domain", ((EventElement)element).getMessageObj().getDomain());
				MDC.put("trx", ((EventElement)element).getMessageObj().getTrx());
				MDC.put("messageKey", messageKey);
				MDC.put("timestamp", ((EventElement)element).getMessageObj().getTimestamp());
				//logger.info("Start Thread " + MDC.get("process_id") + " Processing Message " + message);
				
				element.accept(filterVisitor);
				element.accept(dataTransformVisitor);
				element.accept(messageBuilderVisitor);
				
				MDC.put("executionCode", ProcessCodeEnum.SUCCESS.getCode());
				
			}catch (Exception e) {
				if(e instanceof JsonMappingException || e instanceof JsonProcessingException || e instanceof IOException) {
					MDC.put("executionCode", ProcessCodeEnum.INVALID_INPUT_JSON.getCode());
				}else if(e instanceof MainRuleNotMatchException){
					MDC.put("executionCode", ProcessCodeEnum.TRX_MAINRULE_NOT_MATCH.getCode());
				}else if(e instanceof RuleMessengerException) {
					MDC.put("executionCode", ProcessCodeEnum.MESSENGER_ERROR.getCode());
				}else if(e instanceof RuleNotMatchException) {
					MDC.put("executionCode", ProcessCodeEnum.TRX_TRANSFORM_NOT_MATCH.getCode());
				}else if(e instanceof EventNotFoundException) {
					MDC.put("executionCode", ProcessCodeEnum.TRX_TRANSFORM_NOT_MATCH.getCode());
				}else if(e instanceof DataAlreadyExistException) {
					MDC.put("executionCode", ProcessCodeEnum.TRX_ALREADY_EXIST.getCode());
				}else if(e instanceof EventRequiredRecordsAmountException) {
					MDC.put("executionCode", ProcessCodeEnum.MORE_DATA_NEEDED.getCode());
				}else if(e instanceof MapGenerationException) {
					MDC.put("executionCode", ProcessCodeEnum.INVALID_JSON_QUERY.getCode());
				}else if(e instanceof InvalidEnrichmentDataException) {
					MDC.put("executionCode", ProcessCodeEnum.DATA_ENRICHMENT.getCode());
				}else if(e instanceof InvalidDataFormatException) {
					MDC.put("executionCode", ProcessCodeEnum.DATA_FORMAT.getCode());
				}else if(e instanceof TopicNotMatchException) {
					MDC.put("executionCode", ProcessCodeEnum.TOPIC_NOT_MATCH.getCode());
				}else {
					MDC.put("executionCode", ProcessCodeEnum.GENERAL_ERROR.getCode());
				}
				logger.warn(e.getMessage());
			}finally {
				logger.info("End Thread in " + (Calendar.getInstance().getTimeInMillis() - startMillis) +  " ms");
				MDC.remove("domain");
				MDC.remove("trx");
				MDC.remove("messageKey");
				MDC.remove("timestamp");
				MDC.remove("event");
				MDC.remove("topic");
				MDC.remove("executionCode");
			}
		});
	}
}
