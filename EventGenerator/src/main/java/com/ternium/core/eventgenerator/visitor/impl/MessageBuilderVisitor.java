package com.ternium.core.eventgenerator.visitor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ternium.core.eventgenerator.kafka.service.KafkaService;
import com.ternium.core.eventgenerator.messenger.IMessenger;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
import com.ternium.core.eventgenerator.util.KieServerProperties;
import com.ternium.core.eventgenerator.visitor.Visitor;
import com.ternium.core.eventgenerator.visitor.element.EventElement;

@Component
public class MessageBuilderVisitor implements Visitor{
	private static Logger logger = LoggerFactory.getLogger(MessageBuilderVisitor.class);

    @Autowired
    KafkaService kafkaService;
    
    @Autowired
	IMessenger rulesMessenger;
    
    @Value("${kieserver.maintopicname}")
	private String maintopicname;
    
    @Autowired
	KieServerProperties kieServerProperties;
    
	@Override
	public void visit(EventElement element) throws Exception {
		logger.info("Message received " + element.getMessage());
    	
		MessageVO messageVO = new MessageVO();
		
		messageVO.setGroupName(null);
		messageVO.setContainer(kieServerProperties.getContainerTopic());
		messageVO.setMessage(element.getMessage());
		messageVO.setJsonObj(element.getJsonObj());
				
		rulesMessenger.sendMessage(messageVO);
		
		if(messageVO.getTopic().isEmpty()) {
			throw new Exception("No se retorno regla");
		}
		
    	kafkaService.send(messageVO.getTopic(),element.getMessage());
	}

}
