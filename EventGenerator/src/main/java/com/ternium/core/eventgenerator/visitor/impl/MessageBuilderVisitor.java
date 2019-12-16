package com.ternium.core.eventgenerator.visitor.impl;

import java.util.Calendar;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.exception.TopicNotMatchException;
import com.ternium.core.eventgenerator.kafka.service.KafkaService;
import com.ternium.core.eventgenerator.messenger.IMessenger;
import com.ternium.core.eventgenerator.messenger.vo.KafkaMessage;
import com.ternium.core.eventgenerator.messenger.vo.Message;
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
        
    @Autowired
	KieServerProperties kieServerProperties;
    
	@Override
	public void visit(EventElement element) throws Exception {
		logger.info("Message received " + element.getMessage());
    	
		element.getMessageObj().setEvent(element.getEvent());
		
		Message message = element.getMessageObj();
		MessageVO messageVO = new MessageVO();
		
		messageVO.setGroupName(element.getGroupName());
		messageVO.setContainer(kieServerProperties.getContainer());
		messageVO.setMessage(element.getMessage());
		messageVO.setMessageObj(element.getMessageObj());
		messageVO.setEvent(element.getEvent());
				
		rulesMessenger.sendMessage(messageVO);
		
		if(messageVO.getTopic().isEmpty()) {
			throw new TopicNotMatchException("Error while getting Topic for " + element.getMessage() + " from Rule " + element.getGroupName());
		}
		
		KafkaMessage kafkaMessage = new KafkaMessage(message.getDomain(), message.getEvent(), String.valueOf(Calendar.getInstance().getTimeInMillis()), element.getEventDataMap());
		
		ObjectMapper objectMapper = new ObjectMapper();
		
    	kafkaService.send(messageVO.getTopic(),objectMapper.writeValueAsString(kafkaMessage));
	}

}
