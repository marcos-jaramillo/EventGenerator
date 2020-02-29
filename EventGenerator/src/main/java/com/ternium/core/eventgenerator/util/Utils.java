package com.ternium.core.eventgenerator.util;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * Clase de utileria crear un nuevo mapa apartir de otro con la posibilidad de filtrar los campos a copiar
 *
 */
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
