package com.ternium.core.eventgenerator.visitor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ternium.core.eventgenerator.exception.MainRuleNotMatchException;
import com.ternium.core.eventgenerator.messenger.IMessenger;
import com.ternium.core.eventgenerator.messenger.vo.Message;
import com.ternium.core.eventgenerator.messenger.vo.MessageVO;
import com.ternium.core.eventgenerator.repository.TransactionRepository;
import com.ternium.core.eventgenerator.util.KieServerProperties;
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
			throw new MainRuleNotMatchException("Can not obtain Event from MainRule");
		}
	}
}
