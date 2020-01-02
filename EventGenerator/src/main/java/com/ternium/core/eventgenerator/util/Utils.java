package com.ternium.core.eventgenerator.util;

import java.util.HashMap;
import java.util.Map;

public class Utils {
 public static Map<Object,Object> copyMapFieldsValues(Map originMap, String desiredFields) {
	 Map<Object,Object> dataMapFields = (Map<Object,Object>)originMap;
	 String[] dataFields = null;
	 
	 if(desiredFields != null && !desiredFields.isEmpty()) {
		 dataMapFields = new HashMap<Object,Object>();
		 
		 dataFields = desiredFields.split(",");
		 
		 for(String field : dataFields) {
			 if(field != null && !field.isEmpty()) {
				 dataMapFields.put(field, originMap.get(field));
			 }
		 }
	 }
	 
	 return dataMapFields;
 }
}
