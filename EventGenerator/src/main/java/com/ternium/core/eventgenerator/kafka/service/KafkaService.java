package com.ternium.core.eventgenerator.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
/**
 * 
 * Clase con la funcion de enviar el mensaje del evento a Kafka.
 *
 */
@Service
public class KafkaService {
	private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String message){
    	logger.info("Kafka Sending Message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }
}
