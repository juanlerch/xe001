package com.labs.xe.server;

import javax.servlet.ServletContext;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.shared.Xonst;

@Api(name = "xservice")
public class XService {


	@ApiMethod(name = "hello")	
	public XEIDTO hello(@Named("name")String name) {
		XEDTOFactory factory = new XEDTOFactory();
		XEIDTO dto = factory.create("name");
		return dto;
	}
	
	
	@ApiMethod(name = "request")	
	public XEIDTO request( XEIDTO data, ServletContext context) {
		XEIDTO globals = null;
		XEIATT<XEIDTO> a = data.get(Xonst.XE_GLOBALS);
		if (a!=null) {
			globals = (XEIDTO) a.getValue();
		}
		XtremEngineServer xeserver =  new XtremEngineServer(globals);
		return xeserver.getXgroovy().run(context,data);	
	}
	
	
}
