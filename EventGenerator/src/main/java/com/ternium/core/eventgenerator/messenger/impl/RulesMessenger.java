package com.ternium.core.eventgenerator.messenger.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.drools.core.command.runtime.rule.GetObjectsCommand;
import org.json.JSONObject;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.enums.JsonFieldEnum;
import com.ternium.core.eventgenerator.messenger.IMessenger;
import com.ternium.core.eventgenerator.messenger.vo.Message;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
import com.ternium.core.eventgenerator.util.KieServerProperties;
import org.kie.server.api.model.KieServiceResponse;


@Component
public class RulesMessenger implements IMessenger{
	private static Logger logger = LoggerFactory.getLogger(RulesMessenger.class);
	
	@Autowired
	KieServerProperties kieServerProperties;
	
	@Autowired
	RuleServicesClient rulesClient;
	
	@Autowired
	KieServices kieServices;
	
	public void sendMessage(MessageVO message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Message ruleMessage = new Message();
        try {
        	ruleMessage = message.getMessageObj();
        	
        	KieCommands commandFactory = kieServices.getCommands();
        	
        	GetObjectsCommand getObjectsCommand = new GetObjectsCommand();
            getObjectsCommand.setOutIdentifier("message");
            
            String objectId = Calendar.getInstance().getTimeInMillis() + ruleMessage.getDomain() + ruleMessage.getEvent() + ruleMessage.getTimestamp();
            
            Command<?> insert = commandFactory.newInsert(ruleMessage, objectId);
            logger.info("Going to call the agenda_group " + message.getGroupName());
            Command<?> agendaGroup = commandFactory.newAgendaGroupSetFocus(message.getGroupName());
            Command<?> fireAllRules = commandFactory.newFireAllRules();
            Command<?> getObjects = commandFactory.newGetObjects("message");
            
            Command<?> batchCommand = commandFactory.newBatchExecution(Arrays.asList(insert,agendaGroup, getObjects, fireAllRules));

            ServiceResponse<ExecutionResults> executeResponse = rulesClient.executeCommandsWithResults(message.getContainer(), batchCommand);
        	
            if (executeResponse.getType() == KieServiceResponse.ResponseType.SUCCESS) {
                Message responseMessage = (Message) executeResponse.getResult().getValue(objectId);

                logger.info(("Kie Server Response:" + responseMessage.toString()));
                
                if (responseMessage.getRuleName() != null && !responseMessage.getRuleName().isEmpty()){
                    //Aqui recibira el nombre de la regla que quiere ejecutar
                    message.setGroupName(responseMessage.getRuleName());
                }
                
                if (responseMessage.getTopic() != null && !responseMessage.getTopic().isEmpty()){
                    //Aqui recibira el nombre de la regla que quiere ejecutar
                    message.setTopic(responseMessage.getTopic());
                }
                
                if (responseMessage.getEvent() != null && !responseMessage.getEvent().isEmpty()){
                    //Aqui recibira el nombre de la regla que quiere ejecutar
                    message.setEvent((responseMessage.getEvent()));
                }
                
                if (responseMessage.getData() != null && responseMessage.getData().containsKey("EstatusViaje")){
	                // Message messageResponse = (Message)executeResponse.getResult().getValue("message");
	                message.setEstatusViaje(Integer.parseInt(responseMessage.getData().get("EstatusViaje").toString()));
                }
                
                if (responseMessage.getJsonQuery() != null){
                    //Aqui recibira el nombre de la regla que quiere ejecutar
                    message.setJsonQuery(responseMessage.getJsonQuery());
                }
                
                if(responseMessage.getTranslatorMap() != null && !responseMessage.getTranslatorMap().isEmpty()) {
                	message.setTranslatorMap(responseMessage.getTranslatorMap());
                }
                if(responseMessage.getCache() != null && responseMessage.getCache()) {
                	message.setCache(responseMessage.getCache());
                }
                
                if(responseMessage.getExpectedTrxs() != null){
                	message.setExpectedTrxs(responseMessage.getExpectedTrxs());
                }
                
                if(responseMessage.getMaster() != null) {
                	message.setMaster(responseMessage.getMaster());
                }
                
                if(responseMessage.getTagChild() != null && !responseMessage.getTagChild().isEmpty()) {
                	message.setTagChild(responseMessage.getTagChild());
                }
                
                if(responseMessage.getJsonQueryChild() != null && !responseMessage.getJsonQueryChild().isEmpty()) {
                	message.setJsonQueryChild(responseMessage.getJsonQueryChild());
                }
                
                if(responseMessage.getOutputDataFields() != null && !responseMessage.getOutputDataFields().isEmpty()) {
                	message.setOutputDataFields(responseMessage.getOutputDataFields());
                }
                
                if(responseMessage.getEventDomain() != null && !responseMessage.getEventDomain().isEmpty()) {
                	message.setEventDomain(responseMessage.getEventDomain());
                }
                
            } else {
            	logger.error("Error executing rules. Message: ");
            	logger.error(executeResponse.getMsg());
                message.setEstatusViaje(0);
            }
        } catch (Exception e) {
        	logger.error("Error executing rules. Message: " + e.getMessage(),e);
            message.setEstatusViaje(0);
        }
	}
	
	/*
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
 
	 */
	

}
