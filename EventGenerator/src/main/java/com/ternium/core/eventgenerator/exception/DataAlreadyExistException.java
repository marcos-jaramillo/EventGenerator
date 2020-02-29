package com.ternium.core.eventgenerator.exception;

/**
 * 
 * Esta clase representa la excepcion generada para identificar cuando la informacion de la transaccion ya existe en base de datos. 
 *
 */
public class DataAlreadyExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAlreadyExistException(String message) {
		super(message);
	}
}