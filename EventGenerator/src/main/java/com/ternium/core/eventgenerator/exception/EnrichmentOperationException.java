package com.ternium.core.eventgenerator.exception;

/**
 * 
 * Esta clase representa la excepcion generada para identificar cuando ocurrio un error al procesar la configuracion de enrichments. 
 *
 */
public class EnrichmentOperationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnrichmentOperationException(String message) {
		super(message);
	}
}