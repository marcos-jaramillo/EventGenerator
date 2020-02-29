package com.ternium.core.eventgenerator.exception;

/**
 * 
 * Esta clase representa la excepcion generada para identificar cuando ocurrio un error al procesar la configuracion de formateo de datos. 
 *
 */
public class InvalidDataFormatException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDataFormatException(String message) {
		super(message);
	}
}