package com.ternium.core.eventgenerator.messenger.vo;

import java.util.Map;

public class Message implements java.io.Serializable {
	public String getTrx() {
		return trx;
	}

	public void setTrx(String trx) {
		this.trx = trx;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String domain;
	private String event;
	private String timestamp;
	private Map data;
	private String topic;
	private String ruleName;
	private String trx;
	private Map translatorMap;
	private String jsonQuery;

	@Override
	public String toString() {
		return "Message [domain=" + domain + ", event=" + event + ", timestamp=" + timestamp + ", data=" + data
				+ ", topic=" + topic + ", ruleName=" + ruleName + ", trx=" + trx + ", translatorMap=" + translatorMap + ", jsonQuery=" + jsonQuery + "]";
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Map getTranslatorMap() {
		return translatorMap;
	}

	public void setTranslatorMap(Map translatorMap) {
		this.translatorMap = translatorMap;
	}

	public String getJsonQuery() {
		return jsonQuery;
	}

	public void setJsonQuery(String jsonQuery) {
		this.jsonQuery = jsonQuery;
	}

	public Message(String domain, String event, String timestamp, Map data, String topic, String ruleName, String trx, Map translatorMap, String jsonQuery) {
		super();
		this.domain = domain;
		this.event = event;
		this.timestamp = timestamp;
		this.data = data;
		this.topic = topic;
		this.ruleName = ruleName;
		this.trx = trx;
		this.translatorMap = translatorMap;
		this.jsonQuery = jsonQuery;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}




}
