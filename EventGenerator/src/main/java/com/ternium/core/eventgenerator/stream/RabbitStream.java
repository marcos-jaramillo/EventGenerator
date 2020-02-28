package com.ternium.core.eventgenerator.stream;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import com.ternium.core.eventgenerator.EventGeneratorApplication;
import com.ternium.core.eventgenerator.service.EventGeneratorService;
import com.ternium.core.eventgenerator.util.KeyGenerator;

@EnableBinding(Processor.class)
public class RabbitStream {
	private static Logger logger = LoggerFactory.getLogger(RabbitStream.class); 
	
	@Autowired
	EventGeneratorService messageService;
	
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
    public String recieveMessage(String message) {
		String messageKey = String.valueOf(KeyGenerator.getKey());
		MDC.put("messageKey", messageKey);
		long startMillis = Calendar.getInstance().getTimeInMillis();
		
		logger.info("Received Message " + message);
		messageService.processMessage(message, messageKey, startMillis);
        return "PROCESS" + message;
    }
}
