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
				
				MDC.put("process_id", String.valueOf(Thread.currentThread().getId()));
				logger.info("Thread " + MDC.get("process_id") + " Processing Message " + message);
				
				
				Element element = new EventElement(message);
				
				element.accept(filterVisitor);
				element.accept(dataTransformVisitor);
				element.accept(messageBuilderVisitor);
				
			}catch (Exception e) {
				// TODO: handle exception
				logger.error("Error while processing message " + message, e );
			}
		});
	}
}
