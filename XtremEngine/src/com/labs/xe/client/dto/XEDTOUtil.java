package com.labs.xe.client.dto;

import java.util.List;

public class XEDTOUtil {

	
	
	public static List<String> getAttAsStringList(XEIDTO dto,String attributeName){
		
		XEATTListOfString l =(XEATTListOfString) dto.get(attributeName);
		
		return l.getValue();
	}
	
	
}
