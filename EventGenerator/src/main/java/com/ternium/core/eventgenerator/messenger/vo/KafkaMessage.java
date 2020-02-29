package com.ternium.core.eventgenerator.messenger.vo;

import java.util.Map;
/**
 * 
 * Esta clase tiene la funcion de transportar datos entre la clase Messenger y la clase MessageBuilderVisitor.
 *
 */
public class KafkaMessage {
	private String domain;
	private String event;
	private String timestamp;
	private Map data;
	
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
	@Override
	public String toString() {
		return "KafkaMessage [domain=" + domain + ", event=" + event + ", timestamp=" + timestamp + ", data=" + data
				+ "]";
	}
	public KafkaMessage(String domain, String event, String timestamp, Map data) {
		super();
		this.domain = domain;
		this.event = event;
		this.timestamp = timestamp;
		this.data = data;
	}
	public KafkaMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
