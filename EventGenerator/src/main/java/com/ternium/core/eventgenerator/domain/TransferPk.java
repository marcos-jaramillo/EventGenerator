package com.ternium.core.eventgenerator.domain;

public class TransferPk {
	private String domain;
	private String timestamp;
	private String event;
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	@Override
	public String toString() {
		return "TrasladoPk [domain=" + domain + ", timestamp=" + timestamp + ", event=" + event + "]";
	}
	public TransferPk(String domain, String timestamp, String event) {
		super();
		this.domain = domain;
		this.timestamp = timestamp;
		this.event = event;
	}
	public TransferPk() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
