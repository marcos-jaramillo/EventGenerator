package com.ternium.core.eventgenerator.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.exception.InvalidDataFormatException;
import com.ternium.core.eventgenerator.exception.InvalidEnrichmentDataException;

public class DataFormatsUtils {
	private static Logger logger = LoggerFactory.getLogger(DataFormatsUtils.class);
	
	public static void applyDateDataFormat(Map dataMap, String dataFormats) throws InvalidDataFormatException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map objJson = new HashMap<>();
		Object formatObjectDef = null;
		Map formatMap = null;
		String inputFormat, outputFormat, dateType;
		long timeMillis;
		String keyValue = "";
		
		String resultValue = "";
		
		LocalDateTime localDateTime;
		DateTimeFormatter formatter;
		
		SimpleDateFormat dateFormat;
		Date date;
		Calendar formatCalendar;
		
		try {
			objJson = objectMapper.readValue(dataFormats, Map.class);
			
			Iterator<String> itMap = objJson.keySet().iterator();
			String key="";
			
			while(itMap.hasNext()) {
				key = itMap.next();
				
				keyValue = getValue(dataMap, key);// dataMap.get(key).toString();
				
				resultValue = "";
				
				formatObjectDef = objJson.get(key);
				
				if(formatObjectDef instanceof Map) {
					try {
						formatMap = (Map)formatObjectDef;
						
						inputFormat = formatMap.get(Constants.INPUT_FORMAT).toString();
						outputFormat = formatMap.get(Constants.OUTPUT_FORMAT).toString();
						dateType = formatMap.get(Constants.DATE_TYPE).toString();
						
						if(dateType.equals(Constants.MILLIS)) {
							timeMillis = Long.valueOf(keyValue);
							
							//localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), ZoneId.systemDefault());
							
							formatCalendar = Calendar.getInstance();
							
							formatCalendar.setTimeInMillis(timeMillis);
							
							if(!outputFormat.isEmpty()) {
								/*formatter = DateTimeFormatter.ofPattern(outputFormat);
								
								resultValue = localDateTime.format(formatter);
								*/
								
								
								dateFormat = new SimpleDateFormat(outputFormat);
								resultValue = dateFormat.format(formatCalendar.getTime());
								
								setValue(dataMap, key, resultValue);
								//dataMap.put(key, resultValue);
							}else {
								throw new InvalidDataFormatException("Output_Format is required for field " + key + ", please validate the output_format defined");
							}
							
						}else if(dateType.equals(Constants.FORMAT)) {
							if(!inputFormat.isEmpty() || !outputFormat.isEmpty()) {
								/*
								formatter = DateTimeFormatter.ofPattern(inputFormat);
								
								localDateTime = LocalDateTime.parse(keyValue, formatter);
								
								formatter = DateTimeFormatter.ofPattern(outputFormat);
								resultValue = localDateTime.format(formatter);
								*/
								
								dateFormat = new SimpleDateFormat(inputFormat);
								date = dateFormat.parse(keyValue);
								
								dateFormat = new SimpleDateFormat(outputFormat);
								resultValue = dateFormat.format(date);
								
								setValue(dataMap, key, resultValue);
								//dataMap.put(key, resultValue);
								
							}else {
								throw new InvalidDataFormatException("Input_Format and Output_Format is required for field " + key + ", please validate");
							}
						}
					}catch (Exception e) {
						throw new InvalidDataFormatException("Invalid DataFormat definition for field " + key + ", Map expected");
					}
				}else {
					throw new InvalidDataFormatException("Invalid DataFormat definition for field " + key + ", Map expected");
				}
			}
		}catch (Exception e) {
			throw new InvalidDataFormatException("Error while convert DataFormat Json");
		}
	}
	
	public static String getValue(Map dataMap, String key) throws InvalidDataFormatException{
		String val = key;
		String value = "";
		String[] dataPath;
		Object pathValue = null;
		
		if(val.contains(".")) {
			dataPath = val.split("\\.");
			pathValue = null;
			for(String path : dataPath) {
				if(path!=null && !path.isEmpty()) {
					if(pathValue != null) {
						pathValue = ((Map)pathValue).get(path);
					}else {
						pathValue = dataMap.get(path);
					}
				}
			}
			if(pathValue instanceof Map) {
				throw new InvalidDataFormatException("Plain value required, Complex type object found when evaluate this field " + val );
			}
			value=pathValue.toString();
			
		}else {
			value=dataMap.get(val).toString();
		}
		
		return value;
	}
	
	public static void setValue(Map dataMap, String key, String newValue) throws InvalidDataFormatException{
		String[] dataPath;
		Object pathValue = null;
		Object lastLevel = null;
		String lastpath = "";
		
		if(key.contains(".")) {
			dataPath = key.split("\\.");
			pathValue = null;
			for(String path : dataPath) {
				if(path!=null && !path.isEmpty()) {
					if(pathValue != null) {
						pathValue = ((Map)pathValue).get(path);
					}else {
						pathValue = dataMap.get(path);
					}
					if(pathValue instanceof Map) {
						lastLevel = pathValue;
					}
					lastpath = path;
				}
			}
			if(pathValue instanceof Map) {
				throw new InvalidDataFormatException("Plain value required, Complex type object found when evaluate this field " + key );
			}
			((Map)lastLevel).put(lastpath, newValue);
			
		}else {
			dataMap.put(key, newValue);
		}
	}
}
