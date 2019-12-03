package com.ternium.core.eventgenerator.visitor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ternium.core.eventgenerator.kafka.service.KafkaService;
import com.ternium.core.eventgenerator.visitor.Visitor;
import com.ternium.core.eventgenerator.visitor.element.EventElement;

@Component
public class MessageBuilderVisitor implements Visitor{
	private static Logger logger = LoggerFactory.getLogger(MessageBuilderVisitor.class);

    @Autowired
    KafkaService kafkaService;
    
    @Value("${app.topic.planeacion}")
    private String topicPlaneacion;
    
    @Value("${app.topic.programacion}")
    private String topicProgramacion;
    
    @Value("${app.topic.logistica}")
    private String topicLogistica;
    
	@Override
	public void visit(EventElement element) throws Exception {
		logger.info("Message received " + element.getMessage());
    	
    	kafkaService.send(topicLogistica,element.getMessage());
	}

}
