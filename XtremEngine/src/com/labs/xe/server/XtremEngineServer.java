package com.labs.xe.server;

import java.util.Map;

import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.server.dsl.ui.XUI;
import com.labs.xe.server.xdb.gae.XDBGae;

public class XtremEngineServer {
	
	//ServletContext serverContext;
	XGroovy xgroovy;
	XDBGae     xdb = new XDBGae(); 
	XUtil   util = new XUtil();
	XEDTOFactory dtoFactory = new XEDTOFactory();
	//private Map<String,Base> dsl= new HashMap<String,Base>();
	private XUI xui = new XUI();

	XEIDTO globals;
	
	public XtremEngineServer(XEIDTO globals) {
		this.globals = globals;
		 xgroovy = new XGroovy(this,xui);
	}
	
	
	public XGroovy getXgroovy() {
		return xgroovy;
	}
	
	
	public XDBGae getXdb(){
		return xdb;
		
	}


	public XUtil getUtil() {
		return util;
	}

	public XEDTOFactory getDtoFactory() {
		return dtoFactory;
	}





	public void setXgroovy(XGroovy xgroovy) {
		this.xgroovy = xgroovy;
	}


	public void setXdb(XDBGae xdb) {
		this.xdb = xdb;
	}


	public void setUtil(XUtil util) {
		this.util = util;
	}


	public void setDtoFactory(XEDTOFactory dtoFactory) {
		this.dtoFactory = dtoFactory;
	}


	public XEIDTO getGlobals() {
		return globals;
	}


	public void setGlobals(XEIDTO globals) {
		this.globals = globals;
	}





	
}
