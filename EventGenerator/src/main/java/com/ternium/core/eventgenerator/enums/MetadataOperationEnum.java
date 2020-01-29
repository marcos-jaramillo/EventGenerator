package com.ternium.core.eventgenerator.enums;

import org.springframework.jmx.export.metadata.InvalidMetadataException;

import com.ternium.core.eventgenerator.enrichment.operation.impl.ConcatOperation;
import com.ternium.core.eventgenerator.util.Constants;

public enum MetadataOperationEnum {
	
	
	CONCAT("concat",Constants.STRING,ConcatOperation.class),
	ADDITION("add",Constants.NUMBER,null),
	SUBTRACTION("sub",Constants.NUMBER,null),
	MULTIPLICATION("mult",Constants.NUMBER,null),
	DIVISION("div",Constants.NUMBER,null)
	;
	
	private String name;
	private String type;
	private Class operationClass;

	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Class getOperationClass() {
		return operationClass;
	}

	private MetadataOperationEnum(String name, String type,Class operationClass) {
		this.name = name;
		this.type = type;
		this.operationClass = operationClass;
	}
	
	public static MetadataOperationEnum findByName(String name) {
		for (MetadataOperationEnum at : MetadataOperationEnum.values()) {
            if (name.equals(at.getName())) {
                return at;
            }
        }
		throw new InvalidMetadataException("The operation " + name + " is not recognized");
	}
}
