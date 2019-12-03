package com.ternium.core.eventgenerator.visitor;

public abstract class Element {
	public abstract void accept(Visitor v) throws Exception;
}
