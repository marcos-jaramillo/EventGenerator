package com.ternium.core.eventgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.ternium.core.eventgenerator.util.AgentServiceProperties;
import com.ternium.core.eventgenerator.util.KieServerProperties;
import com.ternium.core.eventgenerator.util.LoggingProperties;
import com.ternium.core.eventgenerator.util.SparkProperties;

@SpringBootApplication
@EnableConfigurationProperties({SparkProperties.class, KieServerProperties.class, AgentServiceProperties.class, LoggingProperties.class})
public class EventGeneratorApplication {
	private static Logger logger = LoggerFactory.getLogger(EventGeneratorApplication.class); 

	@Autowired
	ResourceLoader resourceLoader;
	
	public static void main(String[] args) {
		SpringApplication.run(EventGeneratorApplication.class, args);
	}
    
	@Bean
	public Resource fsmConfig() {
	    return resourceLoader.getResource(
	      "classpath:FSM_configuration.txt");
	}
}
