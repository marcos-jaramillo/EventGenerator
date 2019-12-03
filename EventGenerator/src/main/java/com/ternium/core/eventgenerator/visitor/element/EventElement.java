package com.ternium.core.eventgenerator.visitor.element;

import com.ternium.core.eventgenerator.visitor.Element;
import com.ternium.core.eventgenerator.visitor.Visitor;

public class EventElement extends Element{
	private String message;
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
	public EventElement(String message) {
		super();
		this.message = message;
	}
}
