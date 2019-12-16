package com.ternium.core.eventgenerator.messenger.vo;

import java.util.Map;

public class MessageVO {
	private String message;
	private String outMessage;
	private String groupName;
	private Message messageObj;
	private Integer estatusViaje;
	private String topic;
	private String container;
	private String event;
	private String jsonQuery;
	private String translatorMap;
	private Boolean cache;
	private Integer expectedTrxs;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOutMessage() {
		return outMessage;
	}
	public void setOutMessage(String outMessage) {
		this.outMessage = outMessage;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Message getMessageObj() {
		return messageObj;
	}
	public void setMessageObj(Message messageObj) {
		this.messageObj = messageObj;
	}
	public Integer getEstatusViaje() {
		return estatusViaje;
	}
	public void setEstatusViaje(Integer estatusViaje) {
		this.estatusViaje = estatusViaje;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	public String getJsonQuery() {
		return jsonQuery;
	}
	public void setJsonQuery(String jsonQuery) {
		this.jsonQuery = jsonQuery;
	}
	
	public String getTranslatorMap() {
		return translatorMap;
	}
	public void setTranslatorMap(String translatorMap) {
		this.translatorMap = translatorMap;
	}
	
	public Boolean getCache() {
		if(cache==null) {
			cache = Boolean.FALSE;
		}
		return cache;
	}
	public void setCache(Boolean cache) {
		this.cache = cache;
	}
	
	
	public Integer getExpectedTrxs() {
		return expectedTrxs;
	}
	public void setExpectedTrxs(Integer expectedTrxs) {
		this.expectedTrxs = expectedTrxs;
	}
	@Override
	public String toString() {
		return "MessageVO [message=" + message + ", outMessage=" + outMessage + ", groupName=" + groupName
				+ ", messageObj=" + messageObj + ", estatusViaje=" + estatusViaje + ", topic=" + topic + ", container="
				+ container + ", event=" + event + ", cache=" + cache + ", expectedTrxs=" + expectedTrxs + "]";
	}
	public MessageVO(String message, String outMessage, String groupName, Message messageObj, Integer estatusViaje,
			String topic, String container, String event, String jsonQuery, String translatorMap, Boolean cache, Integer expectedTrxs) {
		super();
		this.message = message;
		this.outMessage = outMessage;
		this.groupName = groupName;
		this.messageObj = messageObj;
		this.estatusViaje = estatusViaje;
		this.topic = topic;
		this.container = container;
		this.event = event;
		this.jsonQuery = jsonQuery;
		this.translatorMap = translatorMap;
		this.cache = cache;
		this.expectedTrxs = expectedTrxs;
	}
	public MessageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
