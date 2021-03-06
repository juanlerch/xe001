package com.labs.xe.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.labs.xe.client.GreetingService;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.shared.Xonst;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	/*
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
	}*/

	/*
	private String  install() { 
		try {
			System.out.println("Instalando el sistema");
			XUtil util=new XUtil();
			util.install();		
			System.out.println("Instalacion completa");
			XDBGae.log(this.getClass().getName() + ".install", "OK");
			return "Instalacion completa";
		}
		catch (Exception e) {
			XDBGae.log(this.getClass().getName() + ".install", e);
	        return "Error al instalar" ;
		}
	}*/

	
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

	/*@Override
	public List<String> getTemplates() {
		try {
			XDBGae xdb = new XDBGae();
			return xdb.getTemplates(null);
		}
		catch (Exception e) {
			e.printStackTrace();
			Logger log = Logger.getGlobal();
	        log.log(Level.SEVERE, "getTemplatesError" + e.getMessage(), e);
	        return null;
		}
	} */

	/*
	@Override
	public XEIDTO query(String template)  {
		try {
			XDBGae xdb = XDBGae.getInstance();
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
		
	}*/
	
/*	@Override
	public void save(XEIDTO dto) {
		try {
			XEIDTO g = null;
			XEIATT<XEIDTO> a = dto.get(Xonst.XE_GLOBALS);
			
			if (a!=null) {
				 g = (XEIDTO) a.getValue();
			}
			XDBGae xdb =  this.getXEServer(g).getXdb();
			xdb.save(dto);
		
		}
		catch (Exception e) {
			Logger log = Logger.getGlobal();
	        log.log(Level.SEVERE, "save" + e.getMessage(), e);
	        e.printStackTrace();
	    	}
	}*/
	
/*	@Override
	public XEIDTO createInstance(String type) {
		XEIDTO g = null;
		//todo:reemplar por generic request
		XDBGae xdb =  this.getXEServer(g).getXdb();
		XEIDTO dto = xdb.createInstance(type);

		return dto;
	}*/
	
	
	private XtremEngineServer getXEServer(XEIDTO globals){
		XtremEngineServer xeserver =  new XtremEngineServer(globals);
		
		return xeserver;
	}
	
	
	@Override
	public XEIDTO request(XEIDTO request) {
		XEIDTO g = null;
		XEIATT<XEIDTO> a = request.get(Xonst.XE_GLOBALS);
		if (a!=null) {
			 g = (XEIDTO) a.getValue();
		}
		return this.getXEServer(g).getXgroovy().run(getServletContext(),request);	
	}
	
	
}
