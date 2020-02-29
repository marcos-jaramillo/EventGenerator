package com.ternium.core.eventgenerator.messenger;

import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
/**
 * 
 * Interfaz de comunicacion con algun otro sistema.
 *
 */
public interface IMessenger {
	public void sendMessage(MessageVO message) throws Exception;
}
