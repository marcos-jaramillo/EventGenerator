package com.ternium.core.eventgenerator.visitor.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.domain.Transaction;
import com.ternium.core.eventgenerator.domain.Transfer;
import com.ternium.core.eventgenerator.enums.JsonFieldEnum;
import com.ternium.core.eventgenerator.exception.DataAlreadyExistException;
import com.ternium.core.eventgenerator.exception.MainRuleNotMatchException;
import com.ternium.core.eventgenerator.messenger.IMessenger;
import com.ternium.core.eventgenerator.messenger.vo.Message;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
import com.ternium.core.eventgenerator.repository.TransactionRepository;
import com.ternium.core.eventgenerator.repository.TransferRepository;
import com.ternium.core.eventgenerator.util.KieServerProperties;
import com.ternium.core.eventgenerator.util.TranslatorUtils;
import com.ternium.core.eventgenerator.visitor.Visitor;
import com.ternium.core.eventgenerator.visitor.element.EventElement;

@Component
public class FilterVisitor implements Visitor{
	private static Logger logger = LoggerFactory.getLogger(FilterVisitor.class);
	
	@Autowired
	IMessenger rulesMessenger;
	/*
	@Autowired
    private SparkSession sparkSession;
	
	@Autowired
	JavaSparkContext javaSparkContext;
	*/
	@Autowired
	TransactionRepository transactionRepository;
	
	@Value("${kieserver.mainrulename}")
	private String mainrulename;
		
	
	@Autowired
	KieServerProperties kieServerProperties;
	
	@Override
	public void visit(EventElement element) throws Exception {
		//Transform Strign message to DataSet
		logger.info("Apply Filter");
		Message message = element.getMessageObj();
		MessageVO messageVO = new MessageVO();
		/*
		SQLContext sqlContext = new SQLContext(javaSparkContext);
		logger.info("SPARK Context Builded");	
		
		List<String> jsonData = Arrays.asList(element.getMessage());
		logger.info("jsonData:=" + jsonData);	
		Dataset<Row> data  = sparkSession.createDataset(jsonData, Encoders.STRING()).toDF();
		data.show();
		logger.info("data:=" + data);
		*/
		messageVO.setGroupName(mainrulename);
		messageVO.setContainer(kieServerProperties.getContainer());
		messageVO.setMessage(element.getMessage());
		messageVO.setMessageObj(message);
				
		rulesMessenger.sendMessage(messageVO);
		
		if(!messageVO.getGroupName().equals(mainrulename)) {
			element.setGroupName(messageVO.getGroupName());
		}else {
			throw new MainRuleNotMatchException("Error while getting ruleName from MainRule");
		}
	}
}
