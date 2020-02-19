package com.ternium.core.eventgenerator.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Transactions")
public class Transaction {	
	@Id
	private TransactionPK id;
	
	private Map<String, Object> data;
	
	private Date creationDate;
	
	public TransactionPK getId() {
		return id;
	}
	public void setId(TransactionPK id) {
		this.id = id;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Transaction(TransactionPK id, Map<String, Object> data) {
		super();
		this.id = id;
		this.data = data;
		this.creationDate = Calendar.getInstance().getTime();
	}
	
	public Transaction(String domain, String trx, String timestamp, Map<String, Object> data) {
		super();
		this.id = new TransactionPK(domain, trx, timestamp);
		this.data = data;
		this.creationDate = Calendar.getInstance().getTime();
	}
	public Transaction() {
		super();
		this.creationDate = Calendar.getInstance().getTime();
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", data=" + data + "]";
	}
}
