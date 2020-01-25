package com.ternium.core.eventgenerator.util;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternium.core.eventgenerator.exception.TranslatorDuplicateKeyException;

public class TranslatorUtils {
	static ObjectMapper objectMapper = new ObjectMapper();
	@SuppressWarnings("unchecked")
	public static Map applyTransalator(Map data, String translator) throws TranslatorDuplicateKeyException{
		Map result =  data;
		Map translatorMap = null;
		
		try {
			translatorMap = objectMapper.readValue(translator, Map.class);
			translatorMap.forEach((k,v)->{
				Object value = "";
				if(result.containsKey(k)){
					value = result.get(k);
					result.remove(k);
					result.put(v, value);
				}
			});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
