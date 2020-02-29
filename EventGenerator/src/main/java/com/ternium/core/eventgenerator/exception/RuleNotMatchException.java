package com.ternium.core.eventgenerator.exception;
/**
 * 
 * Esta clase representa la excepcion generada al no recibir el nombre del agenda group de la siguiente regla a ejecutar.
 * 
 * Esto puedo ocurrir por que no fue configurado en la regla consumida o por que no encontro match.
 *
 */
public class RuleNotMatchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RuleNotMatchException(String message) {
		super(message);
	}
}