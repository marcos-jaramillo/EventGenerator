package com.ternium.core.eventgenerator.exception;
/**
 * 
 * Esta clase representa la excepcion generada para identificar cuando el la informacion de la transaccionno encontro coincidencia 
 * en la regla de Filtro 
 *
 */
public class MainRuleNotMatchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainRuleNotMatchException(String message) {
		super(message);
	}
}