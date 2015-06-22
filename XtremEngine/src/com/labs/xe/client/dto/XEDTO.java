package com.labs.xe.client.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class XEDTO implements XEIDTO {

	 private Map<String,XEIATT> attributes = null;

	 private Map<String, List<XEIDTO>> relations = null;

	 private String name = "NO SET";

	 public XEDTO() {
		// if (name==null) throw new RuntimeException("name can't be null");
	}
	 
	 @Override
	public XEIATT get(String name) {
		 return attributes.get(name);
	}
	 
	 @Override
	public List<XEIDTO> getRel(String name) {
		return relations.get(name);
	}
	 
	 
	 public XEDTO(String name) {
		 
		 	if (name==null) throw new RuntimeException("name can't be null");
	        this.attributes = new HashMap<String, XEIATT>();
	        this.relations = new HashMap<String, List<XEIDTO>>();
	        this.name = name;
	    }

	 public XEDTO add(String name, XEIATT attribute) {
	        this.attributes.put(name, attribute);
	        return this;
	    }
	 
	 @Override
	public XEDTO addRel(String name, XEIDTO dto) {
	
		if(this.relations.get(name) == null) {
	    	this.relations.put(name, new ArrayList<XEIDTO>());
	    }
	    
	    this.relations.get(name).add(dto);
		return this;
	}
	
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
	@Override
	public Set<String> getEntryKey() {
				return this.attributes.keySet();
	}
	
	public Set<String> getRelEntryKey() {
		if(this.relations==null) return null;
		   return this.relations.keySet();
	};
	
	@Override
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public String getValueAsString(String name) {
		XEIATT att = this.get(name);
		Object o = att.getValue();
		String s = (String) o;
		return null;
	}


	@Override
	public String getValueAsLong(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValueAsDouble(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValueAsDate(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
