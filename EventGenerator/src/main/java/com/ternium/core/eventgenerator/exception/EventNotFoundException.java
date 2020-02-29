package com.ternium.core.eventgenerator.exception;

/**
 * 
 * Esta clase representa la excepcion generada para identificar cuando no se pudo obtener el evento de la regla de transformacion. 
 *
 */
public class EventNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EventNotFoundException(String message) {
		super(message);
	}
}