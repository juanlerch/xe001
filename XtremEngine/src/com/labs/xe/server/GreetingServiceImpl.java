package com.labs.xe.server;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.labs.xe.client.GreetingService;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.ui.XUIBase;
import com.labs.xe.client.ui.XUIButton;
import com.labs.xe.server.xdb.XDB;
import com.labs.xe.server.xdb.XDBQuery;
import com.labs.xe.shared.FieldVerifier;
import com.labs.xe.shared.Xonst;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		
		String result = install();

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + " install:  " + result
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	
	private String  install() { 
		try {
			System.out.println("Instalando el sistema");
			XUtil util=new XUtil();
			util.install();		
			System.out.println("Instalacion completa");
			XDB.log(this.getClass().getName() + ".install", "OK");
			return "Instalacion completa";
		}
		catch (Exception e) {
			XDB.log(this.getClass().getName() + ".install", e);
	        return "Error al instalar" ;
		}
	}

	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public List<String> getTemplates() {
		try {
			XDB xdb = new XDB();
			return xdb.getTemplates(null);
		}
		catch (Exception e) {
			e.printStackTrace();
			Logger log = Logger.getGlobal();
	        log.log(Level.SEVERE, "getTemplatesError" + e.getMessage(), e);
	        return null;
		}
	} 

	@Override
	public XEIDTO query(String template)  {
		try {
			XDB xdb = XDB.getInstance();
			XDBQuery query = new XDBQuery();
			query.setTemplate(template);
			XEIDTO dtos =  xdb.query(query);
		return dtos;
		}
		catch (Exception e) {
			Logger log = Logger.getGlobal();
	        log.log(Level.SEVERE, "query" + e.getMessage(), e);
	        e.printStackTrace();
	        return null;
			}
		
	}
	
	@Override
	public void save(XEIDTO dto) {
		try {
			XDB xdb =  this.getXEServer().getXdb();
			xdb.save(dto);
		
		}
		catch (Exception e) {
			Logger log = Logger.getGlobal();
	        log.log(Level.SEVERE, "save" + e.getMessage(), e);
	        e.printStackTrace();
	    	}
	}
	
	@Override
	public XEIDTO createInstance(String type) {
		XDB xdb =  this.getXEServer().getXdb();
		XEIDTO dto = xdb.createInstance(type);

		return dto;
	}
	
	
	public XtremEngineServer getXEServer(){

		final String name="xtremEngineServer";
		
		HttpServletRequest r = this.getThreadLocalRequest();
			
		HttpSession session = r.getSession();

		XtremEngineServer xeserver  = (XtremEngineServer ) session.getAttribute(name);
		
		if (xeserver ==null) { 
			xeserver = new XtremEngineServer();
			session.setAttribute(name, xeserver);
			
		}
		xeserver.setSession(session);
		return xeserver  ;
		
	}
	
	
	@Override
	public XEIDTO request(XEIDTO request) {
		return this.getXEServer().getXgroovy().run(getServletContext(),request);	
	}
	
	
}
