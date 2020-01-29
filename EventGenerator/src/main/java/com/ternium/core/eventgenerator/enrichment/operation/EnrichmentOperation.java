package com.ternium.core.eventgenerator.enrichment.operation;

import java.util.Map;

import com.ternium.core.eventgenerator.exception.EnrichmentOperationException;
import com.ternium.core.eventgenerator.exception.InvalidEnrichmentDataException;

public interface EnrichmentOperation {
	public Object execute(Map dataMap , Map dataOperation, String key) throws EnrichmentOperationException, InvalidEnrichmentDataException;
}
