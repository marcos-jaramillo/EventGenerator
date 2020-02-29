package com.ternium.core.eventgenerator.enrichment.operation.impl;

import java.util.Map;

import com.ternium.core.eventgenerator.enrichment.operation.EnrichmentOperation;
import com.ternium.core.eventgenerator.exception.EnrichmentOperationException;
import com.ternium.core.eventgenerator.exception.InvalidEnrichmentDataException;
import com.ternium.core.eventgenerator.util.Constants;
/**
 * 
 * Esta clase es la implementacion de la interfaz EnrichmentOperation. 
 * Dicta el comportamiento de la operacion concat de 1 o mas campos.
 * 
 * Pendiente de implementacion, siguiente fase.
 *
 */
public class ConcatOperation implements EnrichmentOperation{
	
	@Override
	public Object execute(Map dataMap , Map dataOperation, String key) throws EnrichmentOperationException, InvalidEnrichmentDataException {
		Object value = dataOperation.get(key);
		
		
		
		StringBuffer sb = new StringBuffer();
		/*
		for(String param: params) {
			if(param.contains("\"")) {
				sb.append(param.replaceAll("\"", ""));
			}else {
				try {
					sb.append(data.get(param).toString());
				}catch(NullPointerException e) {
					throw new InvalidEnrichmentDataException("The field " + param + " not found.");
				}
			}
		}
		*/
		return sb.toString();
	}

}
