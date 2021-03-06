package com.ternium.core.eventgenerator.messenger.vo;

/**
 * 
 * Clase de transporte de informacion entre la capa de Visitor y Messenger.
 *
 */
public class MessageVO {
	private String message;
	private String outMessage;
	private String groupName;
	private Message messageObj;
	private Integer estatusViaje;
	private String topic;
	private String container;
	private String event;
	private String jsonQuery;
	private String translatorMap;
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
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOutMessage() {
		return outMessage;
	}
	public void setOutMessage(String outMessage) {
		this.outMessage = outMessage;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Message getMessageObj() {
		return messageObj;
	}
	public void setMessageObj(Message messageObj) {
		this.messageObj = messageObj;
	}
	public Integer getEstatusViaje() {
		return estatusViaje;
	}
	public void setEstatusViaje(Integer estatusViaje) {
		this.estatusViaje = estatusViaje;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getJsonQuery() {
		return jsonQuery;
	}
	public void setJsonQuery(String jsonQuery) {
		this.jsonQuery = jsonQuery;
	}
	public String getTranslatorMap() {
		return translatorMap;
	}
	public void setTranslatorMap(String translatorMap) {
		this.translatorMap = translatorMap;
	}
	public Boolean getCache() {
		if(cache==null) {
			cache = Boolean.FALSE;
		}
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
	
	@Override
	public String toString() {
		return "MessageVO [message=" + message + ", outMessage=" + outMessage + ", groupName=" + groupName
				+ ", messageObj=" + messageObj + ", estatusViaje=" + estatusViaje + ", topic=" + topic + ", container="
				+ container + ", event=" + event + ", jsonQuery=" + jsonQuery + ", translatorMap=" + translatorMap
				+ ", cache=" + cache + ", expectedTrxs=" + expectedTrxs + ", jsonQueryChild=" + jsonQueryChild
				+ ", tagChild=" + tagChild + ", master=" + master + ", outputDataFields=" + outputDataFields
				+ ", eventDomain=" + eventDomain + ", dateFormats=" + dateFormats + ", enrichments=" + enrichments
				+ ", numericFormats=" + numericFormats + ", deleteOnComplete=" + deleteOnComplete + ", deleteByQuery="
				+ deleteByQuery + "]";
	}
	
	public MessageVO(String message, String outMessage, String groupName, Message messageObj, Integer estatusViaje,
			String topic, String container, String event, String jsonQuery, String translatorMap, Boolean cache,
			Integer expectedTrxs, String jsonQueryChild, String tagChild, Boolean master, String outputDataFields,
			String eventDomain, String dateFormats, String enrichments, String numericFormats, Boolean deleteOnComplete, 
			String deleteByQuery) {
		super();
		this.message = message;
		this.outMessage = outMessage;
		this.groupName = groupName;
		this.messageObj = messageObj;
		this.estatusViaje = estatusViaje;
		this.topic = topic;
		this.container = container;
		this.event = event;
		this.jsonQuery = jsonQuery;
		this.translatorMap = translatorMap;
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
	public MessageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
