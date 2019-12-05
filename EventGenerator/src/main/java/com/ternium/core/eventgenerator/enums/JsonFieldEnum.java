package com.ternium.core.eventgenerator.enums;

public enum JsonFieldEnum {
	DOMAIN("domain"),
	TIMESTAMP("timestamp"),
	EVENT("event"),
	DATA("data");

	private String value;

	private JsonFieldEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
