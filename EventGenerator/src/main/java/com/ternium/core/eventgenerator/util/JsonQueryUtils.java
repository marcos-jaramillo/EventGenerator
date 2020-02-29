package com.ternium.core.eventgenerator.util;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * Clase de utileria para obtener las llaves insertadas en un json query
 *
 */
public class JsonQueryUtils {
	public static final String ELEMENT_TAG = "$(";
	
	public static List<String> getFieldsOnQuery(String jsonQuery){
		List<String> result = new ArrayList<String>();
		
		String[] splitQuery = jsonQuery.split(ELEMENT_TAG);
		
		if(splitQuery != null && splitQuery.length>0) {
			for(String split : splitQuery) {
				result.add(split.substring(0, split.indexOf(")")));
			}
		}
		
		return result;
	}
}
