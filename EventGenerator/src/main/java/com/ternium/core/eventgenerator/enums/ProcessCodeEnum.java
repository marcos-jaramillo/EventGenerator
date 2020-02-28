package com.ternium.core.eventgenerator.enums;

public enum ProcessCodeEnum {
	SUCCESS("000000"),
	GENERAL_ERROR("111111"),
	INVALID_INPUT_JSON("000010"), //Invalid input json
	TRX_MAINRULE_NOT_MATCH("000020"),
	MESSENGER_ERROR("000030"),
	TRX_TRANSFORM_NOT_MATCH("000040"),
	TRX_ALREADY_EXIST("000050"),
	MORE_DATA_NEEDED("000060"),
	INVALID_JSON_QUERY("000070"),
	DATA_ENRICHMENT("000090"),
	DATA_FORMAT("000100"),
	TOPIC_NOT_MATCH("000110");

	private String code;

	private ProcessCodeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
