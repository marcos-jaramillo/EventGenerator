package com.ternium.core.eventgenerator.enrichment.operation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.enrichment.operation.impl.ConcatOperation;
import com.ternium.core.eventgenerator.enums.MetadataOperationEnum;
import com.ternium.core.eventgenerator.messenger.vo.Message;
import com.ternium.core.eventgenerator.util.Constants;

public class test {
	public static void main(String[] args) {
		
		
		
		 LocalDate date;
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		  //String text = date.format(formatter);
		  
		  String text = "20191002";
		  LocalDate parsedDate = LocalDate.parse(text, formatter);
		  
		  
		  /*
		   LocalDateTime localDateTime = ;
		LocalDate localDate = LocalDate.now();;
		
		String fecha ="20191002";
		String outputFormat = "yyyy MM dd";
		String inputFormat = "yyyyMMdd";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(outputFormat);
		
		//localDateTime = LocalDateTime.parse(fecha, formatter);
		localDate = LocalDate.parse(fecha, formatter);
		
		System.out.println("localDateTime=" + localDate);
		   */
		  
		/*
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{"+
							"\"NuevoCampo\":{\"concat\":[\"field1\",\"field2\", \"'Campo3'\"]}" + "," 
						+	"\"NuevoCampo2\":{\"concat\":[{\"concat\":[\"field3\",\"field4\"]},\"field2\"]}" 
						+ "}";
		Map data = new HashMap<>();
		Map obj = new HashMap<>();
		
		data.put("field1", "Marcos");
		data.put("field2", "Alain");
		data.put("field3", "Jaramillo");
		data.put("field4", "Arato");
		
		
		try {
			obj = objectMapper.readValue(json, Map.class);
			
			Iterator<String> itMap = obj.keySet().iterator();
			Set<Object> set;
			String key="";
			String value = "";
			
			while(itMap.hasNext()) {
				key = itMap.next();
				value = obj.get(key).toString();
				System.out.println("Field :  " + key);
				//System.out.println("Value :  " + value);
				//System.out.println("Value type " + obj.get(key).getClass());
				//System.out.println(obj.get(key) instanceof Map);
				
				System.out.println(getValue(data, obj, key));
			}
			
			//MetadataOperationEnum.findByName(name)
			//enrichmentOperation.execute(data, json);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		*/
	}
	
	public static String getValue(Map dataMap , Map data, String key) {
		Object value = data.get(key);
		String valueString =null;
		String resultValue = "";
		Map objMap = null;
		List lstvalues;
		String[] values = null;
		
		String keyObj = "";
		Object valueObj = null;
		if(value instanceof Map) {
			objMap = (Map)value;
			Iterator<String> itMap = objMap.keySet().iterator();
			while(itMap.hasNext()) {
				keyObj = itMap.next();
				value = objMap.get(keyObj);
				resultValue+= getValue(dataMap, objMap, keyObj);
			}
		}else if(value instanceof String) {
			valueString = value.toString();
			values = valueString.split(Constants.PARAM_SEPARATOR);
			for(String val :values) {
				if(val != null && !val.isEmpty()) {
					if(val.contains("'")) {
						resultValue+= val.replaceAll("'", "");
					}else {
						resultValue+=dataMap.get(val);
					}
				}
			}
			
			
		}else if(value instanceof List) {
			lstvalues = (List)value;
			for(Object val : lstvalues) {
				if(val instanceof String) {
					if(val != null && !val.toString().isEmpty()) {
						if(val.toString().contains("'")) {
							resultValue+= val.toString().replaceAll("'", "");
						}else {
							resultValue+=dataMap.get(val.toString());
						}
					}
				}else if(val instanceof Map) {
					objMap = (Map)val;
					Iterator<String> itMap = objMap.keySet().iterator();
					while(itMap.hasNext()) {
						keyObj = itMap.next();
						value = objMap.get(keyObj);
						resultValue+= getValue(dataMap, objMap, keyObj);
					}
				}
			}
		}
		
		return resultValue;
	}
}
