package com.ternium.core.eventgenerator.messenger.vo;

import org.json.JSONObject;

public class MessageVO {
	private String message;
	private String outMessage;
	private String groupName;
	private JSONObject jsonObj;
	private Integer estatusViaje;
	private String topic;
	private String container;

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

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
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

	@Override
	public String toString() {
		return "MessageVO [message=" + message + ", outMessage=" + outMessage + ", groupName=" + groupName
				+ ", jsonObj=" + jsonObj + ", estatusViaje=" + estatusViaje + ", container=" + container+ "]";
	}
}
