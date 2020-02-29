package com.ternium.core.eventgenerator.exception;

/**
 * 
 * Esta clase representa la excepcion generada para identificar cuando ocurrio un error al obtener datos para procesar la configuracion de enrichments. 
 *
 */

public class InvalidEnrichmentDataException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEnrichmentDataException(String message) {
		super(message);
	}
}