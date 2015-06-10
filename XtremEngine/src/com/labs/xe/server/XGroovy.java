package com.labs.xe.server;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.Serializable;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.labs.xe.client.admin.XUIManager;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.server.dsl.ui.Base;
import com.labs.xe.server.dsl.ui.XDSLUtil;
import com.labs.xe.server.dsl.ui.XUI;
import com.labs.xe.shared.Xonst;

public class XGroovy 
{
	public static java.util.Map<String,Object> sessionBack = new HashMap<String,Object>();
	
	 XtremEngineServer server;
	 XUI               ui;
	 GroovyShell       groovy;
	 HttpSession       session;
	 ServletContext    context;
	 XDSLUtil           util;
	
	private XGroovy() {
		 // TODO Auto-generated constructor stub
	}
	public XGroovy(XtremEngineServer server,XUI ui) {
		this.server = server;
		this.ui=ui;
		this.session = server.getSession();
	}
	
	
	
	
	public XEIDTO run(ServletContext context,XEIDTO request){
		this.context=context;
		util = new XDSLUtil(context);
		
		String requestType = request.getName();
		Binding binding = new Binding();


		//binding.setProperty(Xonst.XE_UI, this.xui);
		binding.setProperty(Xonst.XE_xtremEngineServer, this);
		//binding.setProperty(Xonst.ServletContext, context);

		//binding.setProperty(Xonst.utl,util );		
		//binding.setProperty("xgroovy", this);

		if (Xonst.REQUEST_TYPE_RUN_SAVED_SCRIPT.equalsIgnoreCase(requestType)){
			String saved_script_name   = request.get(Xonst.saved_script_name).getValue().toString();
			  
			String script =  util.loadSavedScript(saved_script_name);
			
			return runScript(context,binding,script);
		}

		
		if (Xonst.SCRIPT_XLINE.equalsIgnoreCase(requestType)){
			String script =  request.get(Xonst.SCRIPT_script).getValue().toString();
			
			return runScript(context,binding,script);
		}
		
		
		
		if (Xonst.SCRIPT_DSL_SESSION.equalsIgnoreCase(requestType)){
			if (request.get(Xonst.SCRIPT_xui).getValue()!=null){
				String id   =  request.get(Xonst.SCRIPT_xui).getValue().toString();
				String name =  request.get(Xonst.SCRIPT_name).getValue().toString();
				Base base = (Base) this.server.getSession().getAttribute(id);
				binding.setProperty("xur", base);
				String component = base.getClass().getSimpleName();
				/*String script  =  "s="+Xonst.utl+".load('"+component+"','" + id + "')\n"; 
				script  +=  "groovy.evaluate(s)\n"; 
				script  +=  "xur= get '"+id + "'\n";
				script += "groovy.evaluate(xur."+name+")\n";*/
				XDSLUtil xutil = new XDSLUtil(context);
				String script  = xutil.load(component);
				script  +=  "xur= get '"+id + "'\n"; 
				script += "eval(xur."+name+")\n";
				return runScript(context,binding,script);
			}
		}
	
		
		return null;
		
	}
	
	/***************************************************************/
	
	
	public String load(String className){
		return this.util.load(className);
	}

	
	public Object evaluate(String script){
		return this.groovy.evaluate(script);
	}

	public String preProcessScript(String script){
		
		script=script.replace("begin", "'''");
		script=script.replace("end", "'''");
		return script; 
		
	}
	
    public Object get(String key){
		return this.session.getAttribute(key);
		
	}
     
	public void set(String key,Object value){
		
		
		this.session.setAttribute("CURRENT_TIME", System.currentTimeMillis());
		sessionBack.put(key, value);
		
		this.session.setAttribute(key, value);
		while (this.session.getAttribute(key) == null){
			this.session.setAttribute(key, value);
		}
	}
	
	/****************************************************************/

	private XEIDTO  runScript(ServletContext context,Binding binding , String script){
			
		groovy = new GroovyShell(binding);
		
		//binding.setProperty(Xonst.groovy, groovy);
		//binding.setProperty(Xonst.session, server.getSession());
		
		script = this.preProcessScript(script);
		
		binding.setProperty(Xonst.xe_cur_script, script);

	//	XDSLUtil util = (XDSLUtil)binding.getProperty(Xonst.utl);		
		 
		String xeScript = util.load( "XE") ;

			Object result =null;
		try {
			result = groovy.evaluate(xeScript);
		}
		catch(Exception e){
			System.out.println("Script Err:" + script);
			XEIDTO dto = server.getDtoFactory().create(Xonst.SCRIPT_RESULT);
			dto.add(Xonst.SCRIPT_result, server.getDtoFactory().createAttString(e.getMessage()));
			e.printStackTrace();
			return dto;
		}
		
		
		XEIDTO dto = server.getDtoFactory().create(Xonst.SCRIPT_RESULT);
		dto.add(Xonst.SCRIPT_result, server.getDtoFactory().createAttString(result));
		
		
		for (XEIDTO d : ui.getEvents()){
			dto.addRel(XUIManager.XUI_UPDATES, d);
		}
		
		ui.getEvents().clear();
		
				
		return dto;
	}
	
	
	

}
