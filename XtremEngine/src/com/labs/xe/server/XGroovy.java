package com.labs.xe.server;

import groovy.lang.Binding;
import java.util.Stack;

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
import com.labs.xe.client.dto.XEIDTOListener;
import com.labs.xe.server.dsl.ui.Base;
import com.labs.xe.server.dsl.ui.XDSLUtil;
import com.labs.xe.server.dsl.ui.XUI;
import com.labs.xe.server.xdb.XDB;
import com.labs.xe.shared.Xonst;

public class XGroovy 
{
	public static java.util.Map<String,Object> cache = new HashMap<String,Object>();
	
	public static boolean debug=true;
	 XtremEngineServer server;
	 XUI                 ui;
	 XDB				 db;
	 GroovyShell         groovy;
	 XEIDTO  globals;
	 XEIDTO  globalsChanges;
	 ServletContext      context;
	 XDSLUtil            util;
	 XEIDTOFactory dtoFactory = new XEDTOFactory();		
	 

	 Object cursor = null;
	 Object back   = new Stack(); 


	public XGroovy(XtremEngineServer server,XUI ui) {
		this.server = server; 
		this.ui=ui;
		this.globals = server.getGlobals();
		this.globalsChanges = dtoFactory.create(Xonst.XE_GLOBALS_CHANGES);
		this.db=server.getXdb();
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
				XEIDTO d1 = (XEIDTO) request.get(Xonst.XE_GLOBALS).getValue();
				XEIATT<XEIDTO> d2 = d1.get(id);
						
				String name =  d2.getValue().get(Xonst.SCRIPT_name).toString();
				XEIDTO base = (XEIDTO) this.globals.get(id).getValue();
				binding.setProperty("cursor", base);
				String component = base.getName();


				XDSLUtil xutil = new XDSLUtil(context);
				String script  = xutil.load(component);
				script  +=  "cursor= get '"+id + "'\n"; 
				script += "eval(cursor."+name+".value)\n";
				return runScript(context,binding,script);
			}
		}
	
		
		return null;
		
	}
	
	/***************************************************************/

	
	public String xid(){
		return XUI.getNextId();
	}
	
	public XEIDTO createDTO(String name){
		XEIDTO dto = this.dtoFactory.create(name);
		return dto;
	}

	public boolean isGlobal(XEIDTO dto){
		XEIATT att =  dto.get(Xonst.xuid);
		if (att==null) return false;
		
		String xuid = (String) att.getValue();
		
		if (xuid != null && this.globals.get(xuid)!=null){
			return true;
		}
		return false;
	}
	public XEIATT createATT(XEIDTO dto,String attribute,Object value){
		XEIATT a = null;
		if (value instanceof String){
			a = dtoFactory.createAttString(value);
		} 
		else if (value instanceof XEIDTO){
			a = dtoFactory.createAttDTO((XEIDTO) value);
		}
		else
		{
			a = dtoFactory.createAttString(value.toString());
		}
		dto.add(attribute, a);
		if (isGlobal(dto)){
			String xuid = dto.get(Xonst.xuid).getValue().toString();
			this.globalsChanges.add(xuid, dtoFactory.createAttDTO(dto));
		}
		return a;
	}
	
	public XEIDTO createREL(XEIDTO dto,String attribute,XEIDTO value){

		dto.addRel(attribute, value);
		return dto;
	}


	
	
	public String load(String className){
		return this.util.load(className);
	}

	public void out(String s){
		if (debug)
			System.out.println("Eval:\n\n " + s);
	}
	
	public Object evaluate(String script){
		if (script != null)  {
			
			try{
				 Object result = this.groovy.evaluate(script);
				 return result;
			}
			catch (RuntimeException e) {
				this.out ("------ERROR SCRIPT -------");
				this.out ("------ERROR " + e.getMessage());
				this.out (script);
				this.out ("------END SCRIPT -------");
				e.printStackTrace();
			}
		    
		}
		return null;
	}

	public String preProcessScript(String script){
		if (script != null ){
			script=script.replace("begin", "'''");
			script=script.replace("end", "'''");
		}
		return script; 
		
	}
	
	/*
    public Object get(String key){
    	XEIATT a = this.globals.get(key);
		return a.getValue();
		
	}
    

     
	public void set(String key,XEIDTO value){
		XEIATT<XEIDTO > svalue = dtoFactory.createAttDTO(value);
		this.globals.add(key, svalue);
		this.globalsChanges.add(key, svalue);
	}
	*/
	/****************************************************************/

	private XEIDTO  runScript(ServletContext context,Binding binding , String script){
			
		groovy = new GroovyShell(binding);
		
		
		this.globals.addListener(new XEIDTOListener() {
			
			@Override
			public void onChangeRel(String att, XEIDTO value) {
				XGroovy.this.globalsChanges.addRel(att, value);
				
			}
			
			@Override
			public void onChangeAtt(String att, XEIATT value) {
				XGroovy.this.globalsChanges.add(att, value);
			}
		});
		
		
		binding.setProperty("globals",this.globals);
		binding.setProperty("cursor",this.cursor);
		binding.setProperty("back",this.back);
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
			this.out("Script Err:" + script);
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

		XEIATT a = dtoFactory.createAttDTO(this.globalsChanges);
		dto.add(Xonst.XE_GLOBALS_CHANGES, a);
		
		
				
		return dto;
	}
	
	
	

}
