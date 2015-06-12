package com.labs.xe.client.dto;

import java.io.Serializable;



public class XEDTOFactory implements XEIDTOFactory,Serializable{

	public static XEDTOFactory newInstance(){
		return new XEDTOFactory(); 
	}
	
	
	@Override
	public XEIDTO create(String name) {
		XEDTO dto = new XEDTO(name);
		return dto;
	} 
	
	
	@Override
	public XEIATT createAttDTO(XEIDTO value) {
		
		XEATT_XDTO as = new XEATT_XDTO();
		
		as.setValue(value);
		
		return as;
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
