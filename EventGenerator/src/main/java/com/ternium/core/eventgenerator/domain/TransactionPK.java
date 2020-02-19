package com.ternium.core.eventgenerator.domain;

public class TransactionPK {
	
	private String domain;
	private String trx;
	private String timestamp;
	
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getTrx() {
		return trx;
	}
	public void setTrx(String trx) {
		this.trx = trx;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public TransactionPK(String domain, String trx, String timestamp) {
		super();
		this.domain = domain;
		this.trx = trx;
		this.timestamp = timestamp;
	}
	public TransactionPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TransactionPK [domain=" + domain + ", trx=" + trx + ", timestamp=" + timestamp + "]";
	}
}
