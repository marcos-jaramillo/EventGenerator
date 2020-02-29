package com.ternium.core.eventgenerator.visitor;

import com.ternium.core.eventgenerator.visitor.element.EventElement;
/**
 * 
 * Interfaz que forma parte del patron de diseño Visitor
 *
 */
public interface Visitor {
	public void visit(EventElement element) throws Exception;
}
