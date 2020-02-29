package com.ternium.core.eventgenerator;

import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.client.MongoCollection;
import com.ternium.core.eventgenerator.domain.Transaction;
import com.ternium.core.eventgenerator.util.AgentServiceProperties;
import com.ternium.core.eventgenerator.util.KieServerProperties;

/**
 *
 * Esta es la clase principal del proyecto que tiene la funcion de iniciar el contexto de Springboot. 
 * 
 */
@SpringBootApplication
@EnableConfigurationProperties({KieServerProperties.class, AgentServiceProperties.class})
public class EventGeneratorApplication {
	private static Logger logger = LoggerFactory.getLogger(EventGeneratorApplication.class); 

	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	@Autowired
	MongoMappingContext mongoMappingContext;
	
	@Value("${spring.data.mongodb.expiration-duration}")
	int expirationTime;
	
	@Value("${spring.data.mongodb.expiration-time-unit}")
	String expirationTimeUnit;
	
	public static void main(String[] args) {
		SpringApplication.run(EventGeneratorApplication.class, args);
	}
    
	@Bean
	public Resource fsmConfig() {
	    return resourceLoader.getResource(
	      "classpath:FSM_configuration.txt");
	}
	
	@EventListener(ApplicationReadyEvent.class)
	 public void initIndicesAfterStartup() {
		
	     MongoCollection<Document> collection = mongoTemplate.getCollection("Transaction");
	     try {
	    	 IndexOperations indexOps = mongoTemplate.indexOps(Transaction.class);
	    		
	    	 indexOps.dropAllIndexes();
	    	 indexOps.ensureIndex(new Index().on("creationDate", Direction.ASC).expire(new Long(expirationTime), TimeUnit.valueOf(expirationTimeUnit)));
	    	 
		     IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoMappingContext);
		     resolver.resolveIndexFor(Transaction.class).forEach(indexOps::ensureIndex);
	     }catch (Exception e) {
			
	     }
	     
	}
}
