package com.labs.xe.client.dto;

import java.io.Serializable;



public class XEDTOFactory implements XEIDTOFactory,Serializable{

	@Override
	public XEIDTO create(String name) {
		XEDTO dto = new XEDTO(name);
		return dto;
	}
	
	
	@Override
	public XEIATT createAttListOfString(String[] anArray) {
		
		XEATTListOfString as = new XEATTListOfString();
		
		
		if (anArray!=null) for (String o:anArray)  as.getValue().add(o);
		
		return as;
	}

	
	
	@Override
	public XEIATT createAttString(Object object) {
		
		XEATTString as = new XEATTString();
		
		if (object!=null) as.setValue(object.toString());
		
		return as;
	}
	
	
	@Override
	public XEIDTO createSimpleValue(String name,Object value) {
		XEDTO dto = new XEDTO(name);
		dto.add("value",createAttString(value));
		return dto;
	}
	

}
