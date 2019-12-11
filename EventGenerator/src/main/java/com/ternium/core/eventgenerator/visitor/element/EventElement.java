package com.ternium.core.eventgenerator.visitor.element;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.messenger.vo.Message;
import com.ternium.core.eventgenerator.visitor.Element;
import com.ternium.core.eventgenerator.visitor.Visitor;

public class EventElement extends Element{
	private String message;
	private String groupName;
	private String event;
	private Message messageObj;
	private ObjectMapper objectMapper = new ObjectMapper();
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
	
}
