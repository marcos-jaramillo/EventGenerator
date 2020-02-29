package com.ternium.core.eventgenerator.exception;
/**
 * 
 * Esta clase representa la excepcion que se general al ocurrir error al obtener la llaves configuradas en el los jsonquery de la regla de transformacion.
 *
 */
public class MapGenerationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MapGenerationException(String message) {
		super(message);
	}
}