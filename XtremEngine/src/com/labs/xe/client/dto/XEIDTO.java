package com.labs.xe.client.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;





import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface XEIDTO extends IsSerializable,Serializable{

	public String getName();
	public void setName (String name);
	public XEIATT get(String name);
	
	public Set<String> getEntryKey();
	public Set<String> getRelEntryKey();
	
	
	public List<XEIDTO> getRel(String name);
	
	public XEDTO add(String name, XEIATT attribute);
	public XEDTO addRel(String name, XEIDTO dto);

	public String getValueAsString(String name);
	public Long getValueAsLong(String name);
	public Double getValueAsDouble(String name);
	public Date getValueAsDate(String name);
	public Object  getValueAsObject(String name);

	
}