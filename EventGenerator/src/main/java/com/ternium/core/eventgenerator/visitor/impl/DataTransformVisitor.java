package com.ternium.core.eventgenerator.visitor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ternium.core.eventgenerator.messenger.IMessenger;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
import com.ternium.core.eventgenerator.util.KieServerProperties;
import com.ternium.core.eventgenerator.visitor.Visitor;
import com.ternium.core.eventgenerator.visitor.element.EventElement;

@Component
public class DataTransformVisitor implements Visitor{
    private static Logger logger = LoggerFactory.getLogger(DataTransformVisitor.class);

    @Autowired
	IMessenger rulesMessenger;
    
    @Autowired
	KieServerProperties kieServerProperties;
    
	@Override
	public void visit(EventElement element) throws Exception {
		logger.info("Apply Data Transform to " + element.getMessage());
    	
		MessageVO messageVO = new MessageVO();
		
		messageVO.setGroupName(element.getGroupName());
		messageVO.setContainer(kieServerProperties.getContainer());
		messageVO.setMessage(element.getMessage());
		messageVO.setMessageObj(element.getMessageObj());
				
		rulesMessenger.sendMessage(messageVO);
		
		element.setGroupName(messageVO.getGroupName());
		element.setEvent(messageVO.getEvent());
	}

}
