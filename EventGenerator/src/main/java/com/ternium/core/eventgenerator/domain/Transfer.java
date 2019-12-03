package com.ternium.core.eventgenerator.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "traslados")
public class Transfer {
	
	@Id
	private TransferPk id;
	
	private String data;

	public TransferPk getId() {
		return id;
	}

	public void setId(TransferPk id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id + ", data=" + data + "]";
	}

	public Transfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transfer(TransferPk id, String data) {
		super();
		this.id = id;
		this.data = data;
	}
	
	public Transfer(String domain, String timestamp, String event, String data) {
		super();
		this.id = new TransferPk(domain, timestamp, event);
		this.data = data;
	}
	
	
}
