package com.ternium.core.eventgenerator.exception;
/**
 * 
 * Esta clase representa la excepcion generada al intentar comunicacarse con el KIE Server y se recibe un error no esperado.
 *
 */
public class RuleMessengerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RuleMessengerException(String message) {
		super(message);
	}
}