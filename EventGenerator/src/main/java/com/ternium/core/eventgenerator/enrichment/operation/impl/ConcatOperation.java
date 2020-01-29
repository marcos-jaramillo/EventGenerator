package com.ternium.core.eventgenerator.enrichment.operation.impl;

import java.util.Map;

import com.ternium.core.eventgenerator.enrichment.operation.EnrichmentOperation;
import com.ternium.core.eventgenerator.exception.EnrichmentOperationException;
import com.ternium.core.eventgenerator.exception.InvalidEnrichmentDataException;
import com.ternium.core.eventgenerator.util.Constants;

public class ConcatOperation implements EnrichmentOperation{
	String json = "{\"NuevoCampo\":{\"concat\":\"field1,field2,'Campo3'\"},\"NuevoCampo2\":{\"concat\":\"field1,'Campo3'\"},\"NuevoCampo3\":{\"concat\":\"'Campo3','Campo4'\"},\"NuevoCampo4\":\"'Campo3','Campo4'\"}";
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
