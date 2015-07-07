package com.labs.xe.client.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class XEDTO implements XEIDTO {
    
	transient private List<XEIDTOListener> listeners;   
	
  	private Map<String,XEIATT> attributes = null;

	 private Map<String, List<XEIDTO>> relations = null;

	 private String name = "NO SET";

	 
	 
	 @Override
	public void addListener(XEIDTOListener listener) {
		if (this.listeners==null) listeners = new ArrayList<XEIDTOListener>();
		this.listeners.add(listener);
		
	}
	 
	 
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
		    //notificar  a los listener
		    if (this.listeners!= null && this.listeners.size()>0) {
		 		for (XEIDTOListener l:this.listeners){
		 			l.onChangeAtt(this,name, attribute);
		 		}
		 	}
		
	        return this;
	    }
	 
	 @Override
	public XEDTO addRel(String name, XEIDTO dto) {
	
		if(this.relations.get(name) == null) {
	    	this.relations.put(name, new ArrayList<XEIDTO>());
	    }
	    
	    this.relations.get(name).add(dto);
	    
	    //notificar  a los listener
	    if (this.listeners!= null && this.listeners.size()>0) {
	 		for (XEIDTOListener l:this.listeners){
	 			l.onChangeRel(this,name, dto);
	 		}
	 	}
	    
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
		if (att==null) return null;
		Object o = att.getValue();
		String s = (String) o;
		return s;
	}
	
	@Override
	public Object getValueAsObject(String name) {
		XEIATT att = this.get(name);
		if (att==null) return null;
		Object o = att.getValue();

		return o;	
		}


	@Override
	public Long  getValueAsLong(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double  getValueAsDouble(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getValueAsDate(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		return toString(3);
	}
	
	@Override
	public String toString(int deep) {
		String response = "{type:" + name + " attributes: { " ;
		if (deep>0){
			for (String key: this.attributes.keySet()){
				response += " " +  key + ":" + this.attributes.get(key).toString(deep-1) + ",";
			} 
			response +="} relations { ";
			for (String key: this.relations.keySet()){
				response +=   "{" + key + ":" ;
				for (XEIDTO dto:this.relations.get(key)){
					response +=	dto.toString(deep-1) +  "," ;
				}
				response += "}";
			}
			response +="}" ;
		}
		else{
			response += "...";
		}
		
		return response + "}";
	}
	
	

}
