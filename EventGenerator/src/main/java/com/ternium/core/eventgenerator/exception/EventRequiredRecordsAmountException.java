package com.ternium.core.eventgenerator.exception;

/**
 * 
 * Esta clase representa la excepcion generada para identificar que el mensaje de evento no se puede completar debido a que 
 * no se han recibido el total de transacciones necesarias para construir el mensaje del evento.
 *
 */
public class EventRequiredRecordsAmountException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EventRequiredRecordsAmountException(String message) {
		super(message);
	}
}