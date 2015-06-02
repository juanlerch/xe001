package com.labs.xe.server.xdb;



import java.util.HashMap;
import java.util.Map;

import com.google.appengine.api.datastore.Entity;

public class XDBEntity {
	String id;
	String template;
	Entity entity;
	
	Map<String,String> values = new HashMap<String,String>();

	public XDBEntity(String template) {
		this.template=template;
	}
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setEntity(Entity entity) {
		this.entity = entity;
	}


	public void setAttribute(String name, String value) {
		values.put(name, value);
	}


	public void setTemplate(String template) {
		this.template=template;
		
	}
	
	
	public String getTemplate() {
		return template;
	}
	
	public Map<String, String> getValues() {
		return values;
	}
	
}
