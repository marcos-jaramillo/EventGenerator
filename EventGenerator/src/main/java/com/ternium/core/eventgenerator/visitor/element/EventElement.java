package com.ternium.core.eventgenerator.visitor.element;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.messenger.vo.KafkaMessage;
import com.ternium.core.eventgenerator.messenger.vo.Message;
import com.ternium.core.eventgenerator.visitor.Element;
import com.ternium.core.eventgenerator.visitor.Visitor;

public class EventElement extends Element{
	private String message;
	private String groupName;
	private String event;
	private Message messageObj;
	private KafkaMessage kafkaMessage;
	private ObjectMapper objectMapper = new ObjectMapper();
	private Boolean cache;
	private Map eventDataMap; 
	private String eventDomain;
	//Transform Strign message to DataSet
	
	@Override
	public void accept(Visitor v) throws Exception{
		v.visit(this);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) throws JsonMappingException, JsonProcessingException {
		this.message = message;
		this.messageObj = objectMapper.readValue(message, Message.class);
	}
	
	public Message getMessageObj() {
		return messageObj;
	}
	public void setMessageObj(Message messageObj) {
		this.messageObj = messageObj;
	}
	public EventElement(String message) throws JsonMappingException, JsonProcessingException {
		super();
		this.message = message;
		this.messageObj = objectMapper.readValue(message, Message.class);
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public Boolean getCache() {
		return cache;
	}
	public void setCache(Boolean cache) {
		this.cache = cache;
	}
	public KafkaMessage getKafkaMessage() {
		return kafkaMessage;
	}
	public void setKafkaMessage(KafkaMessage kafkaMessage) {
		this.kafkaMessage = kafkaMessage;
	}
	
	public Map getEventDataMap() {
		return eventDataMap;
	}
	public void setEventDataMap(Map eventDataMap) {
		this.eventDataMap = eventDataMap;
	}
	
	public String getEventDomain() {
		return eventDomain;
	}
	public void setEventDomain(String eventDomain) {
		this.eventDomain = eventDomain;
	}
	public void objToMessage() {
		ObjectMapper objectMapper = new ObjectMapper();
		
    	try {
			this.message = objectMapper.writeValueAsString(messageObj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
