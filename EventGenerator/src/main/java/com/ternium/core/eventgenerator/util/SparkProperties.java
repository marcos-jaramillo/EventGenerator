package com.ternium.core.eventgenerator.util;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class SparkProperties {
	@Value("${spark.config.clusterName}")
    private String clusterName;
	
	@Value("${spark.config.host}")
    private String host;
	
	@Value("${spark.config.port}")
    private String port;
	
	@Value("${spark.config.master}")
    private String master;
	
	@Value("${spark.config.appName}")
    private String appName;
	
	@Value("${spark.config.userName}")
    private String userName;
	
	@Value("${spark.config.userPassword}")
    private String userPassword;
	
	@Value("${spark.config.inputCl}")
    private String inputCl;
	
	@Value("${spark.config.outputCl}")
    private String outputCl;
	
	@Value("${spark.config.retryCount}")
    private String retryCount;
	
	@Value("${spark.config.ssl}")
    private String ssl;
	
	@Bean
    public SparkConf sparkConf() {
        SparkConf sparkConf = new SparkConf()
                .setAppName(appName)
                //.setSparkHome(sparkHome)
                .setMaster(master);

        return sparkConf;
    }
	
	@Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkConf());
    }

    @Bean
    public SparkSession sparkSession() {
        return SparkSession
                .builder()
                .sparkContext(javaSparkContext().sc())
                .appName(appName)
                .getOrCreate();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
	public String getClusterName() {
		return clusterName;
	}


	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}


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


	public String getMaster() {
		return master;
	}


	public void setMaster(String master) {
		this.master = master;
	}


	public String getAppName() {
		return appName;
	}


	public void setAppName(String appName) {
		this.appName = appName;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getInputCl() {
		return inputCl;
	}


	public void setInputCl(String inputCl) {
		this.inputCl = inputCl;
	}


	public String getOutputCl() {
		return outputCl;
	}


	public void setOutputCl(String outputCl) {
		this.outputCl = outputCl;
	}


	public String getRetryCount() {
		return retryCount;
	}


	public void setRetryCount(String retryCount) {
		this.retryCount = retryCount;
	}


	public String getSsl() {
		return ssl;
	}


	public void setSsl(String ssl) {
		this.ssl = ssl;
	}


	@Override
	public String toString() {
		return "SparkProperties{" +
	            "clusterName='" + clusterName + '\'' +
	            ", host='" + host + '\'' +
	            ", port='" + port + '\'' +
	            ", master='" + master + '\'' +
	            ", appName='" + appName + '\'' +
	            ", userName='" + userName + '\'' +
	            ", userPassword='" + userPassword + '\'' +
	            ", inputCl='" + inputCl + '\'' +
	            ", retryCount='" + retryCount + '\'' +
	            ", ssl='" + ssl + '\'' +
	            '}';
	}
}


