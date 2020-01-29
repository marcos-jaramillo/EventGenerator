package com.ternium.core.eventgenerator.messenger.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.drools.core.command.runtime.rule.FireAllRulesCommand;
import org.drools.core.command.runtime.rule.InsertObjectCommand;
import org.kie.api.KieServices;
import org.kie.api.command.BatchExecutionCommand;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.messenger.IMessenger;
import com.ternium.core.eventgenerator.messenger.vo.Message;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;


@Component
public class RulesMessenger implements IMessenger{
	private static Logger logger = LoggerFactory.getLogger(RulesMessenger.class);
	
	@Autowired
	StatelessKieSession statelessKieSession;
	
	public void sendMessage(MessageVO message) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Message ruleMessage = new Message();
		com.droolsrulesternium.ternium.Message transportMessage;
        try {
        	ruleMessage = message.getMessageObj();
            String objectId = Calendar.getInstance().getTimeInMillis() + ruleMessage.getDomain() + ruleMessage.getEvent() + ruleMessage.getTimestamp();

            List<Command<?>> cmds = new ArrayList<>();
            KieCommands commands = KieServices.Factory.get().getCommands();
            cmds.add(commands.newAgendaGroupSetFocus(message.getGroupName()));
            
            transportMessage = new com.droolsrulesternium.ternium.Message(ruleMessage.getDomain(), ruleMessage.getEvent(), ruleMessage.getTimestamp(), ruleMessage.getData(),
            															ruleMessage.getTopic(), ruleMessage.getRuleName(), ruleMessage.getTrx(), ruleMessage.getTranslatorMap(),
            															ruleMessage.getJsonQuery(), ruleMessage.getCache(), ruleMessage.getExpectedTrxs(), ruleMessage.getJsonQueryChild(),
            															ruleMessage.getTagChild(), ruleMessage.getMaster(), ruleMessage.getOutputDataFields(), ruleMessage.getEventDomain(), 
            															ruleMessage.getDateFormats(), ruleMessage.getEnrichments(), ruleMessage.getNumericFormats());
            cmds.add(new InsertObjectCommand(transportMessage,objectId));
            cmds.add(new FireAllRulesCommand());
            logger.info("Sending Object: " + transportMessage.toString());
            logger.info("Going to call the agenda_group " + message.getGroupName());
            


            BatchExecutionCommand myCommands = CommandFactory.newBatchExecution(cmds);
            ExecutionResults response = statelessKieSession.execute(myCommands);
            
        //  if (response.getType() == KieServiceResponse.ResponseType.SUCCESS) {
            com.droolsrulesternium.ternium.Message responseMessage = (com.droolsrulesternium.ternium.Message) response.getValue(objectId);
            

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
            
            if(responseMessage.getDateFormats() != null && !responseMessage.getDateFormats().isEmpty()) {
            	message.setDateFormats(responseMessage.getDateFormats());
            }
            
        	if(responseMessage.getEnrichments() != null && !responseMessage.getEnrichments().isEmpty()) {
            	message.setEnrichments(responseMessage.getEnrichments());
            }

			if(responseMessage.getNumericFormats() != null && !responseMessage.getNumericFormats().isEmpty()) {
				message.setNumericFormats(responseMessage.getNumericFormats());
			}
            
//        } else {
//        	logger.error("Error executing rules. Message: ");
//        	logger.error(executeResponse.getMsg());
//            message.setEstatusViaje(0);
//        }
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("error rules " , e);
		}
	}
	
	/*
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
            //logger.info("Going to call the agenda_group " + message.getGroupName());
            Command<?> agendaGroup = commandFactory.newAgendaGroupSetFocus(message.getGroupName());
            Command<?> fireAllRules = commandFactory.newFireAllRules();
            Command<?> getObjects = commandFactory.newGetObjects("message");
            
            Command<?> batchCommand = commandFactory.newBatchExecution(Arrays.asList(insert,agendaGroup, getObjects, fireAllRules));

            ServiceResponse<ExecutionResults> executeResponse = rulesClient.executeCommandsWithResults(message.getContainer(), batchCommand);
        	
            if (executeResponse.getType() == KieServiceResponse.ResponseType.SUCCESS) {
                Message responseMessage = (Message) executeResponse.getResult().getValue(objectId);

                //logger.info(("Kie Server Response:" + responseMessage.toString()));
                
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
            	//logger.error("Error executing rules. Message: ");
            	//logger.error(executeResponse.getMsg());
                message.setEstatusViaje(0);
            }
        } catch (Exception e) {
        	logger.error("Error executing rules. Message: " + e.getMessage(),e);
            message.setEstatusViaje(0);
        }
	}
	*/
	
	

}
