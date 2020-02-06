package com.ternium.core.eventgenerator.messenger.vo;

import java.util.Map;

public class Message implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String domain;
	private String event;
	private String timestamp;
	private Map data;
	private String topic;
	private String ruleName;
	private String trx;
	private String translatorMap;
	private String jsonQuery;
	private Boolean cache;
	private Integer expectedTrxs;
	private String jsonQueryChild;
	private String tagChild;
	private Boolean master;
	private String outputDataFields;
	private String eventDomain;
	
	private String dateFormats;
	private String enrichments;
	private String numericFormats;
	
	private Boolean deleteOnComplete;
	private String deleteByQuery;

	

	@Override
	public String toString() {
		return "Message [domain=" + domain + ", event=" + event + ", timestamp=" + timestamp + ", data=" + data
				+ ", topic=" + topic + ", ruleName=" + ruleName + ", trx=" + trx + ", translatorMap=" + translatorMap
				+ ", jsonQuery=" + jsonQuery + ", cache=" + cache + ", expectedTrxs=" + expectedTrxs
				+ ", jsonQueryChild=" + jsonQueryChild + ", tagChild=" + tagChild + ", master=" + master
				+ ", outputDataFields=" + outputDataFields + ", eventDomain=" + eventDomain + ", dateFormats="
				+ dateFormats + ", enrichments=" + enrichments + ", numericFormats=" + numericFormats
				+ ", deleteOnComplete=" + deleteOnComplete + ", deleteByQuery=" + deleteByQuery + "]";
	}

	public Boolean getDeleteOnComplete() {
		return deleteOnComplete;
	}

	public void setDeleteOnComplete(Boolean deleteOnComplete) {
		this.deleteOnComplete = deleteOnComplete;
	}

	public String getDeleteByQuery() {
		return deleteByQuery;
	}

	public void setDeleteByQuery(String deleteByQuery) {
		this.deleteByQuery = deleteByQuery;
	}

	public String getTrx() {
		return trx;
	}

	public void setTrx(String trx) {
		this.trx = trx;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getTranslatorMap() {
		return translatorMap;
	}

	public void setTranslatorMap(String translatorMap) {
		this.translatorMap = translatorMap;
	}

	public String getJsonQuery() {
		return jsonQuery;
	}

	public void setJsonQuery(String jsonQuery) {
		this.jsonQuery = jsonQuery;
	}

	public Boolean getCache() {
		return cache;
	}
	public void setCache(Boolean cache) {
		this.cache = cache;
	}
	
	public Integer getExpectedTrxs() {
		return expectedTrxs;
	}
	public void setExpectedTrxs(Integer expectedTrxs) {
		this.expectedTrxs = expectedTrxs;
	}
	
	public String getJsonQueryChild() {
		return jsonQueryChild;
	}

	public void setJsonQueryChild(String jsonQueryChild) {
		this.jsonQueryChild = jsonQueryChild;
	}

	public String getTagChild() {
		return tagChild;
	}

	public void setTagChild(String tagChild) {
		this.tagChild = tagChild;
	}

	public Boolean getMaster() {
		return master;
	}

	public void setMaster(Boolean master) {
		this.master = master;
	}


	public String getOutputDataFields() {
		return outputDataFields;
	}

	public void setOutputDataFields(String outputDataFields) {
		this.outputDataFields = outputDataFields;
	}
		
	

	public Message(String domain, String event, String timestamp, Map data, String topic, String ruleName, String trx,
			String translatorMap, String jsonQuery, Boolean cache, Integer expectedTrxs, String jsonQueryChild,
			String tagChild, Boolean master, String outputDataFields, String eventDomain, String dateFormats,
			String enrichments, String numericFormats, Boolean deleteOnComplete, String deleteByQuery) {
		super();
		this.domain = domain;
		this.event = event;
		this.timestamp = timestamp;
		this.data = data;
		this.topic = topic;
		this.ruleName = ruleName;
		this.trx = trx;
		this.translatorMap = translatorMap;
		this.jsonQuery = jsonQuery;
		this.cache = cache;
		this.expectedTrxs = expectedTrxs;
		this.jsonQueryChild = jsonQueryChild;
		this.tagChild = tagChild;
		this.master = master;
		this.outputDataFields = outputDataFields;
		this.eventDomain = eventDomain;
		this.dateFormats = dateFormats;
		this.enrichments = enrichments;
		this.numericFormats = numericFormats;
		this.deleteOnComplete = deleteOnComplete;
		this.deleteByQuery = deleteByQuery;
	}

	public String getEventDomain() {
		return eventDomain;
	}

	public void setEventDomain(String eventDomain) {
		this.eventDomain = eventDomain;
	}
	
	public String getDateFormats() {
		return dateFormats;
	}

	public void setDateFormats(String dateFormats) {
		this.dateFormats = dateFormats;
	}

	public String getEnrichments() {
		return enrichments;
	}

	public void setEnrichments(String enrichments) {
		this.enrichments = enrichments;
	}

	public String getNumericFormats() {
		return numericFormats;
	}

	public void setNumericFormats(String numericFormats) {
		this.numericFormats = numericFormats;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
}
