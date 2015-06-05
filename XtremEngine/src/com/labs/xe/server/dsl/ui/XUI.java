package com.labs.xe.server.dsl.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.modules.ModulesService;
import com.google.appengine.api.modules.ModulesServiceFactory;
import com.labs.xe.client.admin.XUIManager;
import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.ui.XUIButton;

import groovy.lang.GroovyShell;

public class XUI extends Base{

	private XEDTOFactory factory = new XEDTOFactory();
	
	List<XEIDTO> events = new ArrayList<XEIDTO>();
	
	String s = "s"; //Servidor
	static long count = 0;
	
	//Base view ;
	
	public XUI() {
		this.xuid = getNextId(); 
	}
	
	
	
	static public synchronized String getNextId(){
		String instance="intance_s00c61b117c4824b69c98957d7e8cc53db170f96c42";
		try{
		ModulesService modulesApi = ModulesServiceFactory.getModulesService();
		instance = "_" + modulesApi.getCurrentInstanceId() + "_";
		}
		catch (Exception e) {
			instance = "_no_instance_s00c61b117c4824b69c98957d7e8cc53db170f96c42_";
		}
		return "s"  +  instance  + count++;
	}
	

	public TextArea textArea(){
		TextArea b = new TextArea(this);
		return b;
	}


	public Button button(){
		Button b = new Button(this);
		return b;
	}
	
	public Panel panel(){
		Panel b = new Panel(this);
	/*	if (view==null) {
			view = b;
		}*/
		return b;
	}
	
	public DialogBox dialogBox(){
		DialogBox dialog = new DialogBox(this);
		
		return dialog;
	}
	
	
	
	
	
	public List<XEIDTO> getEvents() {
		return events;
	}
	

	public void update(Base base,String what, Base value) {
		XEIDTO dto = factory.create(XUIManager.XUI_UPDATES);
		 
		dto.add(XUIManager.XUI_id,factory.createAttString(base.xuid));
		
		String  class1 = base.getClass().getSimpleName();;
		dto.add(XUIManager.XUI_Type,factory.createAttString(class1 ));
		dto.add(XUIManager.XUI_isNew,factory.createAttString(false));
		dto.add(XUIManager.XUI_update_name,factory.createAttString(what));
		dto.add(XUIManager.XUI_update_value, factory.createAttString(value.xuid));

		this.events.add(dto); 
		
	}

	public void update(Base base,String what, String value) {
		String xid = base.xuid; 
		String class1 = base.getClass().getSimpleName();
		this.update(xid, class1,what,value); 
		
	}

		
	public void update(String xid, Class class1, boolean isNew) {
		String type = class1.getSimpleName();
		
		XEIDTO dto = factory.create(XUIManager.XUI_UPDATES);
		
		dto.add(XUIManager.XUI_id,factory.createAttString(xid));
		dto.add(XUIManager.XUI_Type,factory.createAttString(type));
		dto.add(XUIManager.XUI_isNew,factory.createAttString(isNew));
		
		this.events.add(dto);
		
	}

	public void update(String xid, String class1, String what,String value) {
		
		XEIDTO dto = factory.create(XUIManager.XUI_UPDATES);
		
		dto.add(XUIManager.XUI_id,factory.createAttString(xid));
		dto.add(XUIManager.XUI_Type,factory.createAttString(class1));
		dto.add(XUIManager.XUI_isNew,factory.createAttString(false));
		dto.add(XUIManager.XUI_update_name,factory.createAttString(what));
		dto.add(XUIManager.XUI_update_value, factory.createAttString(value));

		this.events.add(dto);
		
	}

	
	public void update(String xid, Class class1, String what,String value) {
		String type = class1.getSimpleName();
		
		XEIDTO dto = factory.create(XUIManager.XUI_UPDATES);
		
		dto.add(XUIManager.XUI_id,factory.createAttString(xid));
		dto.add(XUIManager.XUI_Type,factory.createAttString(type));
		dto.add(XUIManager.XUI_isNew,factory.createAttString(false));
		dto.add(XUIManager.XUI_update_name,factory.createAttString(what));
		
		dto.add(XUIManager.XUI_update_value, factory.createAttString(value));
		this.events.add(dto);
		
	}


	
}
