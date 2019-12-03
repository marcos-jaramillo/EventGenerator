package com.ternium.core.eventgenerator.visitor.impl;

import java.util.Arrays;
import java.util.List;
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
import org.springframework.stereotype.Component;

import com.ternium.core.eventgenerator.domain.Transfer;
import com.ternium.core.eventgenerator.exception.DataAlreadyExistException;
import com.ternium.core.eventgenerator.messenger.Messenger;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
import com.ternium.core.eventgenerator.repository.TransferRepository;
import com.ternium.core.eventgenerator.visitor.Visitor;
import com.ternium.core.eventgenerator.visitor.element.EventElement;

@Component
public class FilterVisitor implements Visitor{
	private static Logger logger = LoggerFactory.getLogger(FilterVisitor.class);
	
	@Autowired
	Messenger rulesMessenger;
	
	@Autowired
    private SparkSession sparkSession;
	
	@Autowired
	JavaSparkContext javaSparkContext;
	
	@Autowired
	TransferRepository transferRepository;
	
	@Override
	public void visit(EventElement element) throws Exception {
		//Transform Strign message to DataSet
		logger.info("Recieve message " + element.getMessage());
		
		SQLContext sqlContext = new SQLContext(javaSparkContext);
		logger.info("SPARK Context Builded");	
		
		List<String> jsonData = Arrays.asList(element.getMessage());
		logger.info("jsonData:=" + jsonData);	
		Dataset<Row> data  = sparkSession.createDataset(jsonData, Encoders.STRING()).toDF();
		data.show();
		logger.info("data:=" + data);	
        
		JSONObject jsonObj = new JSONObject(element.getMessage());
		Transfer transfer = new Transfer(jsonObj.getString("domain"), jsonObj.getString("timestamp"), jsonObj.getString("event"), jsonObj.getJSONObject("data").toString());
		
		if(!transferRepository.findById(transfer.getId()).isPresent()) {
			transferRepository.save(transfer);
		}else {
			logger.info("Data Already Exist");
			throw new DataAlreadyExistException("Data Already Exist for " + jsonObj.toString());
		}
		
		MessageVO messageVO = new MessageVO();
		
		messageVO.setMessage(element.getMessage());
		
		rulesMessenger.sendMessage(messageVO);
	}

}
