package com.ternium.core.eventgenerator.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
/**
 * 
 * Clase que almacena las propiedades de informacion del Agent Service
 *
 */

@Configuration
@PropertySource("classpath:agentservice.properties")
@ConfigurationProperties(prefix = "agentservice", ignoreUnknownFields = false)
public class AgentServiceProperties {
	private String host;
	
	private String port;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	
}
