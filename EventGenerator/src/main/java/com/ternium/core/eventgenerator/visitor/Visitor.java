package com.ternium.core.eventgenerator.visitor;

import com.ternium.core.eventgenerator.visitor.element.EventElement;

public interface Visitor {
	public void visit(EventElement element) throws Exception;
}
