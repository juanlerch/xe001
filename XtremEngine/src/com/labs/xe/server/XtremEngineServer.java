package com.labs.xe.server;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.server.dsl.ui.XUI;
import com.labs.xe.server.xdb.XDB;

public class XtremEngineServer implements Serializable{
	
	//ServletContext serverContext;
	XGroovy xgroovy;
	XDB     xdb = new XDB(); 
	XUtil   util = new XUtil();
	XEDTOFactory dtoFactory = new XEDTOFactory();
	//private Map<String,Base> dsl= new HashMap<String,Base>();
	XUI xui = new XUI();

	transient HttpSession session;
	
	public XtremEngineServer() {
		 xgroovy = new XGroovy(this);
	}
	
	
	public XGroovy getXgroovy() {
		return xgroovy;
	}
	
	
	public XDB getXdb(){
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


	public void setXdb(XDB xdb) {
		this.xdb = xdb;
	}


	public void setUtil(XUtil util) {
		this.util = util;
	}


	public void setDtoFactory(XEDTOFactory dtoFactory) {
		this.dtoFactory = dtoFactory;
	}


	public XUI getXUI() {
		return this.xui;
	}



	public XUI getXui() {
		return xui;
	}


	public void setXui(XUI xui) {
		this.xui = xui;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}


	
}
