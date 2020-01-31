package com.ternium.core.eventgenerator.enrichment.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.exception.InvalidEnrichmentDataException;
import com.ternium.core.eventgenerator.util.Constants;

public class EnrichmentUtils {
	private static Logger logger = LoggerFactory.getLogger(EnrichmentUtils.class);
	
	public static void processEnrichments(Map dataMap, String enrichments) throws InvalidEnrichmentDataException{
		ObjectMapper objectMapper = new ObjectMapper();
		Map objJson = new HashMap<>();
		String dataFieldValue = "";
		try {
			objJson = objectMapper.readValue(enrichments, Map.class);
			
			Iterator<String> itMap = objJson.keySet().iterator();
			String key="";
			
			while(itMap.hasNext()) {
				dataFieldValue="";
				key = itMap.next();
				
				dataFieldValue = getValue(dataMap, objJson, key);
				
				dataMap.put(key, dataFieldValue);
				logger.info("Generate Field " + key + " with value " + dataFieldValue);
			}
		}catch (Exception e) {
			throw new InvalidEnrichmentDataException("Error while evalute enrichments");
		}
	}
	
	public static String getValue(Map dataMap , Map data, String key) throws InvalidEnrichmentDataException{
		Object value = data.get(key);
		String valueString =null;
		String resultValue = "";
		Map objMap = null;
		List lstvalues;
		String[] values = null;
		String[] dataPath;
		Object pathValue = null;
		
		
		String keyObj = "";
		Object valueObj = null;
		try {
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
									throw new InvalidEnrichmentDataException("Plain value required, Complex type object found when evaluate this field " + val );
								}
								resultValue+=pathValue.toString();
								
							}else {
								resultValue+=dataMap.get(val);
							}
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
								if(val.toString().contains(".")) {
									dataPath = val.toString().split("\\.");
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
										throw new InvalidEnrichmentDataException("Plain value required, Complex type object found when evaluate this field " + val.toString() );
									}
									resultValue+=pathValue.toString();
									
								}else {
									resultValue+=dataMap.get(val.toString());
								}
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
		}catch (Exception e) {
			throw new InvalidEnrichmentDataException("Can not evaluate this field " + key );
		}
		return resultValue;
	}
}
