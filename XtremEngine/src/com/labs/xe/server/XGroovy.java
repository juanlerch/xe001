package com.labs.xe.server;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.gwt.dev.util.DefaultTextOutput;
import com.labs.xe.client.admin.XUIManager;
import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.dto.XEIDTOFactory;
import com.labs.xe.server.dsl.ui.Base;
import com.labs.xe.server.dsl.ui.XDSLUtil;
import com.labs.xe.server.dsl.ui.XUI;
import com.labs.xe.shared.Xonst;

public class XGroovy 
{
	public static java.util.Map<String,Object> sessionBack = new HashMap<String,Object>();
	
	 XtremEngineServer server;
	 XUI                 ui; 
	 GroovyShell         groovy;
	 XEIDTO  globals;
	 XEIDTO  globalsChanges;
	 ServletContext      context;
	 XDSLUtil            util;
		XEIDTOFactory dtoFactory = new XEDTOFactory();		


	public XGroovy(XtremEngineServer server,XUI ui) {
		this.server = server; 
		this.ui=ui;
		this.globals = server.getGlobals();
		this.globalsChanges = dtoFactory .create(Xonst.XE_GLOBALS_CHANGES);

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
				Base base = (Base) this.server.getGlobals().get(id);
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
		return this.globals.get(key);
		
	}
    

     
	public void set(String key,XEIDTO value){
		XEIATT<XEIDTO > svalue = dtoFactory.createAttDTO(value);
		this.globals.add(key, svalue);
		this.globalsChanges.add(key, svalue);
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
		
		/*Events to UI*/
		for (XEIDTO d : ui.getEvents()){
			dto.addRel(XUIManager.XUI_UPDATES, d);
		}
		
		ui.getEvents().clear();

		/*Globals to UI*/
		for (XEIDTO d : ui.getEvents()){
			dto.addRel(XUIManager.XUI_UPDATES, d);
		}

				
		return dto;
	}
	
	
	

}
