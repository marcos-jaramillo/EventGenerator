package com.ternium.core.eventgenerator.enrichment.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.enums.MetadataOperationEnum;
import com.ternium.core.eventgenerator.exception.InvalidEnrichmentDataException;
import com.ternium.core.eventgenerator.util.Constants;

/**
 * 
 * Esta clase de utiliades procesa la configuracion de enrichments que se congfiguro en la regla de Transform en KIE Server.
 * 
 * Actualmente solo tiene implementado la operacion concat
 * 
 * Pendiente agregar funcionalidad para ejecutar la implementacion de la operacion recibida en la configuracion de enrichments 
 *
 */
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
				
				dataFieldValue = getValue(dataMap, objJson, key, true);
				
				dataMap.put(key, dataFieldValue);
				logger.info("Generate Field " + key + " with value " + dataFieldValue);
			}
		}catch (Exception e) {
			throw new InvalidEnrichmentDataException("Error while evalute enrichments " + e);
		}
	}
	
	public static String getValue(Map dataMap , Map data, String key, boolean isFiedDef) throws InvalidEnrichmentDataException{
		Object value = data.get(key);
		String valueString =null;
		String resultValue = "";
		Map objMap = null;
		List lstvalues;

		String keyObj = "";
		try {
			if(value instanceof Map) {
				//CALL OPERATION, ONLY THE OPERATION TYPE CAN BE A MAP OBJECT
				if(!isFiedDef) {
					MetadataOperationEnum operationEnum = MetadataOperationEnum.findByName(key);
				}
				objMap = (Map)value;
				Iterator<String> itMap = objMap.keySet().iterator();
				while(itMap.hasNext()) {
					keyObj = itMap.next();
					value = objMap.get(keyObj);
					resultValue+= getValue(dataMap, objMap, keyObj, false);
				}
			}else if(value instanceof String) {
				//THIS WILL BE A FINAL VALUE 
				valueString = value.toString();
				resultValue+= getStringValue(valueString, dataMap);
			}else if(value instanceof List) {
				//THIS WILL BE THE OPERATION DEFINITION, THIS KIND OF OBJECT IS USED TO DEFINE A OPERATION BEHAIVOR
				//Move code to operation class
				lstvalues = (List)value;
				for(Object val : lstvalues) {
					resultValue+= getValue(dataMap, data, keyObj, false);
				}
				resultValue+= resolveOperation(lstvalues, dataMap);
				
				
			}
		}catch (Exception e) {
			throw new InvalidEnrichmentDataException("Can not evaluate this field " + key + ", caused by " + e.getMessage());
		}
		return resultValue;
	}
	
	public static String getStringValue(String valueString, Map dataMap) throws InvalidEnrichmentDataException{
		String resultValue = "";
		String[] dataPath;
		Object pathValue = null;
		if(valueString != null && !valueString.isEmpty()) {
			if(valueString.contains(Constants.CHARACTER_QUOTE)) {
				resultValue+= valueString.replaceAll(Constants.CHARACTER_QUOTE, Constants.EMPTY_STRING);
			}else {
				if(valueString.contains(Constants.OBJECT_PATH_LEVEL_SEPARATOR)) {
					dataPath = valueString.split(Constants.ESCAPE_OBJECT_PATH_LEVEL_SEPARATOR);
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
						throw new InvalidEnrichmentDataException("Plain value required, Complex type object found when evaluate this field " + valueString);
					}
					resultValue+=pathValue.toString();
					
				}else {
					resultValue+=dataMap.get(valueString);
				}
			}
		}
		return resultValue;
	}
	
	public static String resolveOperation(List operationParams, Map dataMap) throws  InvalidEnrichmentDataException{
		String resultValue = "";
		Map objMap = null;
		String keyObj = "";
		Object valueObj = null;
		Object value;
		for(Object val : operationParams) {
			if(val instanceof String) {
				resultValue += getStringValue(val.toString(), dataMap);
			}else if(val instanceof Map) {
				objMap = (Map)val;
				Iterator<String> itMap = objMap.keySet().iterator();
				while(itMap.hasNext()) {
					keyObj = itMap.next();
					value = objMap.get(keyObj);
					resultValue+= getValue(dataMap, objMap, keyObj, false);
				}
			}
		}
		return resultValue;
	}
}
