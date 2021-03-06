package com.labs.xe.client.dto;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface XEIDTOFactory /*extends AutoBeanFactory */{

	
	
	XEIDTO create (String name);
	
	XEIDTO createSimpleValue(String name, Object value);
	
	XEIATT createAttString(Object object);

	XEIATT createAttListOfString(String[] object);

	XEIATT createAttDTO(XEIDTO value);
	
	
	
	
	
}
