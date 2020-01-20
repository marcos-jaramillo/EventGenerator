package com.ternium.core.eventgenerator.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

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
	
	public void processMessage(String message, String messageKey) {
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
				
				logger.info("End Thread");
			}catch (Exception e) {
				// TODO: handle exception
				logger.warn(e.getMessage());
			}finally {
				MDC.remove("domain");
				MDC.remove("trx");
				MDC.remove("messageKey");
				MDC.remove("timestamp");
				MDC.remove("event");
				MDC.remove("topic");
			}
		});
	}
}
