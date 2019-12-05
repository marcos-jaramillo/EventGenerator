package com.ternium.core.eventgenerator.messenger;

import com.ternium.core.eventgenerator.messenger.vo.MessageVO;

public interface IMessenger {
	public void sendMessage(MessageVO message) throws Exception;
}
