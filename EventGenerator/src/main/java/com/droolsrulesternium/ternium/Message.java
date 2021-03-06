package com.droolsrulesternium.ternium;

/**
 * 
 * Esta clase sirve como medio de transporte de datos entre el proyecto EventGenerator y KIE Server.
 * 
 */

public class Message implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private java.lang.String domain;
	private java.lang.String trx;
	private java.lang.String event;
	private java.lang.String topic;
	private java.lang.String timestamp;
	private java.util.Map data;
	private String ruleName;
	private java.lang.String jsonQuery;
	private String translatorMap;
	private java.lang.String outputDataFields;

	private java.lang.Boolean cache;
	private java.lang.Integer expectedTrxs;
	private java.lang.String dataSelect;
	
	private java.lang.String jsonQueryChild;
	private java.lang.String tagChild;
	private java.lang.Boolean master;
	private java.lang.String eventDomain;
	
	private java.lang.String dateFormats;
	private java.lang.String enrichments;
	private java.lang.String numericFormats;
	
	private java.lang.Boolean deleteOnComplete;
	private java.lang.String deleteByQuery;
	

	public Message() {
	}

	public java.lang.String getDomain() {
		return this.domain;
	}
	public java.util.Map getData() {
		return this.data;
	}
	public void setData(java.util.Map data) {
		this.data = data;
	}
	public void setDomain(java.lang.String domain) {
		this.domain = domain;
	}

	public java.lang.String getEvent() {
		return this.event;
	}

	public void setEvent(java.lang.String event) {
		this.event = event;
	}

	public java.lang.String getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(java.lang.String timestamp) {
		this.timestamp = timestamp;
	}

	public java.lang.String getTopic() {
		return this.topic;
	}

	public void setTopic(java.lang.String topic) {
		this.topic = topic;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}


	@Override
	public String toString() {
		return "Message [domain=" + domain + ", trx=" + trx + ", event=" + event + ", topic=" + topic + ", timestamp="
				+ timestamp + ", data=" + data + ", ruleName=" + ruleName + ", jsonQuery=" + jsonQuery
				+ ", translatorMap=" + translatorMap + ", outputDataFields=" + outputDataFields + ", cache=" + cache
				+ ", expectedTrxs=" + expectedTrxs + ", dataSelect=" + dataSelect + ", jsonQueryChild=" + jsonQueryChild
				+ ", tagChild=" + tagChild + ", master=" + master + ", eventDomain=" + eventDomain + ", dateFormats="
				+ dateFormats + ", enrichments=" + enrichments + ", numericFormats=" + numericFormats
				+ ", deleteOnComplete=" + deleteOnComplete + ", deleteByQuery=" + deleteByQuery + "]";
	}

	public java.lang.String getTrx() {
		return this.trx;
	}

	public void setTrx(java.lang.String trx) {
		this.trx = trx;
	}


	public java.lang.String getJsonQuery() {
		return this.jsonQuery;
	}

	public void setJsonQuery(java.lang.String jsonQuery) {
		this.jsonQuery = jsonQuery;
	}

	public java.lang.String getTranslatorMap() {
		return this.translatorMap;
	}

	public void setTranslatorMap(java.lang.String translatorMap) {
		this.translatorMap = translatorMap;
	}

	public java.lang.Boolean getCache() {
		return this.cache;
	}

	public void setCache(java.lang.Boolean cache) {
		this.cache = cache;
	}

	public java.lang.Integer getExpectedTrxs() {
		return this.expectedTrxs;
	}

	public void setExpectedTrxs(java.lang.Integer expectedTrxs) {
		this.expectedTrxs = expectedTrxs;
	}

	public java.lang.String getDataSelect() {
		return this.dataSelect;
	}

	public void setDataSelect(java.lang.String dataSelect) {
		this.dataSelect = dataSelect;
	}

    public java.lang.String getJsonQueryChild() {
		return jsonQueryChild;
	}

	public void setJsonQueryChild(java.lang.String jsonQueryChild) {
		this.jsonQueryChild = jsonQueryChild;
	}

	public java.lang.String getTagChild() {
		return tagChild;
	}

	public void setTagChild(java.lang.String tagChild) {
		this.tagChild = tagChild;
	}

	public java.lang.Boolean getMaster() {
		return master;
	}

	public void setMaster(java.lang.Boolean master) {
		this.master = master;
	}

	public String getOutputDataFields() {
		return outputDataFields;
	}

	public void setOutputDataFields(String outputDataFields) {
		this.outputDataFields = outputDataFields;
	}
	
	public java.lang.String getEventDomain() {
		return this.eventDomain;
	}
	public void setEventDomain(java.lang.String eventDomain) {
		this.eventDomain = eventDomain;
	}

	public java.lang.String getDateFormats() {
		return this.dateFormats;
	}
	public void setDateFormats(java.lang.String dateFormats) {
		this.dateFormats = dateFormats;
	}
	
	public java.lang.String getEnrichments() {
		return this.enrichments;
	}
	public void setEnrichments(java.lang.String enrichments) {
		this.enrichments = enrichments;
	}
	
	public java.lang.String getNumericFormats() {
		return this.numericFormats;
	}
	public void setNumericFormats(java.lang.String numericFormats) {
		this.numericFormats = numericFormats;
	}
	
	public java.lang.Boolean getDeleteOnComplete() {
		return deleteOnComplete;
	}

	public void setDeleteOnComplete(java.lang.Boolean deleteOnComplete) {
		this.deleteOnComplete = deleteOnComplete;
	}

	public java.lang.String getDeleteByQuery() {
		return deleteByQuery;
	}

	public void setDeleteByQuery(java.lang.String deleteByQuery) {
		this.deleteByQuery = deleteByQuery;
	}

	public Message(String domain, String event, String timestamp, java.util.Map data, String topic, String ruleName, String trx,
			String translatorMap, String jsonQuery, Boolean cache, Integer expectedTrxs, String jsonQueryChild,
			String tagChild, Boolean master, String outputDataFields, java.lang.String eventDomain, java.lang.String dateFormats,
			java.lang.String enrichments, java.lang.String numericFormats, java.lang.Boolean deleteOnComplete, java.lang.String deleteByQuery) {
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
}