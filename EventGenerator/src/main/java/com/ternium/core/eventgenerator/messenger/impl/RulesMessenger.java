package com.ternium.core.eventgenerator.messenger.impl;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.server.api.model.ServiceResponse;
import org.kie.server.client.RuleServicesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ternium.core.eventgenerator.messenger.Messenger;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
import com.ternium.core.eventgenerator.util.KieServerProperties;

@Component
public class RulesMessenger implements Messenger{
	private static Logger logger = LoggerFactory.getLogger(RulesMessenger.class);
	
	@Autowired
	KieServerProperties kieServerProperties;
	
	@Autowired
	RuleServicesClient rulesClient;
	
	@Autowired
	KieServices kieServices;
	
	public void sendMessage(MessageVO message) throws Exception {
		logger.info("Going to send the message " + message);
		List<Command<?>> commands = new ArrayList<>();
		logger.info("Going to send message to " + kieServerProperties.getServerUrl() + "::" + kieServerProperties.getUser() + "::" + kieServerProperties.getUserCredential() + "::" + kieServerProperties.getContainer());
		
		KieCommands commandFactory = kieServices.getCommands();
		logger.info("Going to commandFactory DONE!!");
		commands.add(commandFactory.newInsert(message.getMessage()));
		commands.add(commandFactory.newFireAllRules());
		
		
		logger.info("Going to BEFORE batchExecutionCommand  DONE!!");
		BatchExecutionCommand batchExecutionCommand = commandFactory.newBatchExecution(commands);
		logger.info("Going to AFTER batchExecutionCommand  DONE!!");
		ServiceResponse<ExecutionResults> response = rulesClient.executeCommandsWithResults(kieServerProperties.getContainer(), batchExecutionCommand);
		logger.info("Going to response  DONE!!");
		ExecutionResults results = response.getResult();
		logger.info("Going to results  DONE!!" + results.getIdentifiers());
		
        logger.info("Message sent with response " );
	}
	
	
	

}
