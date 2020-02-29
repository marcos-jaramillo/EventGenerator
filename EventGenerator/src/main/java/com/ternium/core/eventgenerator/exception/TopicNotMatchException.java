package com.ternium.core.eventgenerator.exception;
/**
 * 
 * Esta clase representa a la Excepcion que se genera al no encontrar un topico en la regla de topicos.
 *
 */
public class TopicNotMatchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TopicNotMatchException(String message) {
		super(message);
	}
}