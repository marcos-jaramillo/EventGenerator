package com.ternium.core.eventgenerator.visitor.element;

import org.json.JSONObject;

import com.ternium.core.eventgenerator.visitor.Element;
import com.ternium.core.eventgenerator.visitor.Visitor;

public class EventElement extends Element{
	private String message;
	private JSONObject jsonObj;
	private String groupName;
	
	@Override
	public void accept(Visitor v) throws Exception{
		v.visit(this);
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public JSONObject getJsonObj() {
		return jsonObj;
	}
	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	public EventElement(String message) {
		super();
		this.message = message;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
