package com.ternium.core.eventgenerator.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.text.TextStringBuilder;
import org.apache.commons.text.matcher.StringMatcher;
import org.apache.commons.text.matcher.StringMatcherFactory;

import com.ternium.core.eventgenerator.exception.MapGenerationException;
/**
 * 
 * Clase de utelerias sobre mapas
 *
 */
public class MapUtils {
	public static Map createKeyMapFronJsonQuery(String jsonQuery) throws MapGenerationException{
		Map keyMap = new HashMap<>();
		
		final StringMatcher pfxMatcher  = StringMatcherFactory.INSTANCE.stringMatcher("${");
		final StringMatcher suffMatcher = StringMatcherFactory.INSTANCE.stringMatcher("}");
		final char escape = '\\';
		
		TextStringBuilder buf = new TextStringBuilder(jsonQuery);
		
		int length = jsonQuery.length();
		int pos = 0;
		int offset = 0;
		char[] chars = buf.toCharArray();
		int bufEnd = offset + length;
		String key;
		
		try {
			while (pos < bufEnd) {
				key = "";
				final int startMatchLen = pfxMatcher.isMatch(chars, pos, offset, bufEnd);
				if (startMatchLen == 0) {
		            pos++;
		        } else {
		        	if (pos > offset && chars[pos - 1] == escape) {
	                    pos++;
	                    continue;
		        	}else {
		        		// find suffix
		        		final int startPos = pos;
		        		pos += startMatchLen;
		        		int endMatchLen = 0;
		        		int nestedVarCount = 0;
		        		
		        		while (pos < bufEnd) {
		        			endMatchLen = suffMatcher.isMatch(chars, pos, offset, bufEnd);
		        			if (endMatchLen == 0) {
		        				pos++;
		        			} else {
		        				// found variable end marker
	        					String varNameExpr = new String(chars, startPos + startMatchLen, pos - startPos - startMatchLen);
	        					pos += endMatchLen;
	        					final int endPos = pos;
	        					
	        					keyMap.put(varNameExpr, null);
	        					break;
		        			}
		        			pos += endMatchLen;
		        		}
		        	}
		        }
			}
		} catch (Exception e) {
			throw new MapGenerationException("Error while evaluate jsonquery: " + e.getMessage());
		}
		return keyMap;
	}
	
	public static void obtainKeysFromMap(Map keysMap, Map dataMap) throws MapGenerationException {
		Iterator<String> itMap = keysMap.keySet().iterator();
		String keyName="";
		String[] dataPath;
		Object pathValue = null;
		
		try {
			while(itMap.hasNext()) {
				keyName = itMap.next();
				if(keyName.contains(Constants.OBJECT_PATH_LEVEL_SEPARATOR)) {
					dataPath = keyName.split(Constants.ESCAPE_OBJECT_PATH_LEVEL_SEPARATOR);
					pathValue = null;
					for(String path : dataPath) {
						if(path!=null && !path.isEmpty()) {
							System.out.println("path " + path);
							if(pathValue != null) {
								pathValue = ((Map)pathValue).get(path);
							}else {
								pathValue = dataMap.get(path);
							}
							if(pathValue instanceof String) {
								keysMap.put(keyName, pathValue);
							}
						}
					}
				}else {
					keysMap.put(keyName, dataMap.get(keyName));
				}
			}
		} catch (Exception e) {
			throw new MapGenerationException("Error while filling values " + e.getMessage());
		}
	}
}
