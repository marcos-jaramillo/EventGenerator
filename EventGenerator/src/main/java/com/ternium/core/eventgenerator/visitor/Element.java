package com.ternium.core.eventgenerator.visitor;
/**
 * 
 * Clase abstracta que forma parte del patron de diseño Visitor
 *
 */
public abstract class Element {
	public abstract void accept(Visitor v) throws Exception;
}
