package com.ternium.core.eventgenerator.util;

import java.io.InputStream;

import org.drools.core.io.impl.UrlResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Clase que almacena la configuracion de la conexion del KIE Server 
 *
 */
@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class KieServerProperties {
	@Value("${kieserver.url}")
	private String serverUrl;
	
	@Value("${kieserver.user}")
	private String user;
	
	@Value("${kieserver.password}")
	private String userCredential;
	
	@Value("${kieserver.container}")
	private String container;
	
	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserCredential() {
		return userCredential;
	}

	public void setUserCredential(String userCredential) {
		this.userCredential = userCredential;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}
	
	@Bean
	public StatelessKieSession statelessKieSession() {
		StatelessKieSession kieSession;
        try{
            String url = serverUrl;//"http://localhost:8080/business-central/maven2/com/stateless/ternium/1.0.0/ternium-1.0.0.jar";
            KieServices kieServices = KieServices.Factory.get();
            KieRepository kieRepository = kieServices.getRepository();
            UrlResource urlResource = (UrlResource) kieServices.getResources().newUrlResource(url);
            urlResource.setUsername(user);
            urlResource.setPassword(userCredential);
            urlResource.setBasicAuthentication("enabled");
            InputStream is = urlResource.getInputStream();
            KieModule kModule = kieRepository.addKieModule(kieServices.getResources().newInputStreamResource(is));
            KieContainer kieContainer = kieServices.newKieContainer(kModule.getReleaseId());
            	

            kieSession = kieContainer.getKieBase().newStatelessKieSession();
           
            return kieSession;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
	}
	/*
	@Bean
	public KieServices kieServices() {
		return KieServices.Factory.get();
	}

	@Bean
	public CredentialsProvider credentialsProvider(){
		return new EnteredCredentialsProvider(user, userCredential);
	}
	
	@Bean
	@Autowired
	public KieServicesConfiguration kieServicesConfig(CredentialsProvider credentialsProvider) {
		return KieServicesFactory.newRestConfiguration(serverUrl, credentialsProvider);
	}
	
	@Bean
	@Autowired
	public KieServicesClient kieServicesClient(KieServicesConfiguration kieServicesConfig) {
		Set<Class<?>> allClasses = new HashSet<Class<?>>();
        allClasses.add(Message.class);
        kieServicesConfig.addExtraClasses(allClasses);
		return KieServicesFactory.newKieServicesClient(kieServicesConfig);
	}
	
	@Bean
	@Autowired
	public RuleServicesClient rulesClient(KieServicesClient kieServicesClient) {
		return  kieServicesClient.getServicesClient(RuleServicesClient.class);
	}
	*/
}
