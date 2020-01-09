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
	
	public void processMessage(String message) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			try {
				Element element = new EventElement(message);
				MDC.put("process_id", String.valueOf(Thread.currentThread().getId()));
				MDC.put("trx", ((EventElement)element).getMessageObj().getTrx());
				
				
				
				logger.info("Start Thread " + MDC.get("process_id") + " Processing Message " + message);
				
				element.accept(filterVisitor);
				element.accept(dataTransformVisitor);
				element.accept(messageBuilderVisitor);
				
				logger.info("End Thread " + MDC.get("process_id"));
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("Error while processing message " + message, e );
			}
		});
	}
}
