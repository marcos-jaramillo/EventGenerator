package com.ternium.core.eventgenerator.visitor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ternium.core.eventgenerator.messenger.Messenger;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
import com.ternium.core.eventgenerator.visitor.Visitor;
import com.ternium.core.eventgenerator.visitor.element.EventElement;

@Component
public class DataTransformVisitor implements Visitor{
    private static Logger logger = LoggerFactory.getLogger(DataTransformVisitor.class);

    @Autowired
	Messenger rulesMessenger;
    
	@Override
	public void visit(EventElement element) throws Exception {
		logger.info("Apply Data Transform to " + element.getMessage());
    	
		MessageVO messageVO = new MessageVO();
		
		messageVO.setMessage(element.getMessage());
    	rulesMessenger.sendMessage(messageVO);
	}

}
