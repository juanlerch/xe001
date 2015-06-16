package com.labs.xe.server.dsl.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.modules.ModulesService;
import com.google.appengine.api.modules.ModulesServiceFactory;
import com.labs.xe.client.admin.XUIManager;
import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.ui.XUIButton;
import com.labs.xe.client.ui.XUIDialogBox;
import com.labs.xe.client.ui.XUIDockPanel;
import com.labs.xe.client.ui.XUIHTML;
import com.labs.xe.client.ui.XUITextArea;
import com.labs.xe.shared.Xonst;

public class XUI extends Base{

	private XEDTOFactory factory = new XEDTOFactory();
	
	private List<XEIDTO> events1 = new ArrayList<XEIDTO>();
	
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
		TextArea b = new TextArea();
		
		b.xuid = XUI.getNextId();
		this.update(b.xuid,XUITextArea.class,true);

		return b;
	}
	
	public HTML HTML(){
		HTML b = new HTML();
		b.xuid = XUI.getNextId();
		this.update(b.xuid,XUIHTML.class,true);

		return b;
	}
	

	public Button button(boolean isNew){
		Button b = new Button();
		b.xuid = XUI.getNextId();
		if (isNew) 
			{
				this.update(b.xuid,XUIButton.class,true);
			}
		return b;
	}
	
	public Panel panel(){
		Panel b = new Panel();

		b.xuid = XUI.getNextId();
		this.update(b.xuid,XUIDockPanel.class,true);
		return b;
	}
	
	public DialogBox dialogBox(){
		DialogBox dialog = new DialogBox();
		dialog.xuid = XUI.getNextId();
		this.update(dialog.xuid,XUIDialogBox.class,true);

		
		return dialog;
	}
	
	
	
	
	
	public List<XEIDTO> getEvents() {
		return events1;
	}
	

	public void update(Base base,String what, Base value) {
		XEIDTO dto = factory.create(XUIManager.XUI_UPDATES);
		 
		dto.add(XUIManager.XUI_id,factory.createAttString(base.xuid));
		
		String  class1 = base.getClass().getSimpleName();;
		dto.add(XUIManager.XUI_Type,factory.createAttString(class1 ));
		dto.add(XUIManager.XUI_isNew,factory.createAttString(false));
		dto.add(XUIManager.XUI_update_name,factory.createAttString(what));
		dto.add(XUIManager.XUI_update_value, factory.createAttString(value.xuid));

		this.events1.add(dto); 
		
	}

	public void update(XEIDTO base,String what, XEIDTO value) {
		XEIDTO dto = factory.create(XUIManager.XUI_UPDATES);
		 
		dto.add(XUIManager.XUI_id,factory.createAttString(base.get(Xonst.xuid)));
		
		String  class1 = base.getClass().getSimpleName();;
		dto.add(XUIManager.XUI_Type,factory.createAttString(class1 ));
		dto.add(XUIManager.XUI_isNew,factory.createAttString(false));
		dto.add(XUIManager.XUI_update_name,factory.createAttString(what));
		dto.add(XUIManager.XUI_update_value, factory.createAttString(value.get(Xonst.xuid)));

		this.events1.add(dto); 
		
	}
	
	public void update(Base base,String what, String value) {
		String xid = base.xuid; 
		String class1 = base.getClass().getSimpleName();
		this.update(xid, class1,what,value); 
		
	}

	public void update(XEIDTO dto,String what, String value) {
		String xid = dto.get(Xonst.xuid).toString(); 
		String class1 = dto.getName();
		this.update(xid, class1,what,value); 
		
	}	
	public void update(String xid, Class class1, boolean isNew) {
		String type = class1.getSimpleName();
		
		XEIDTO dto = factory.create(XUIManager.XUI_UPDATES);
		
		dto.add(XUIManager.XUI_id,factory.createAttString(xid));
		dto.add(XUIManager.XUI_Type,factory.createAttString(type));
		dto.add(XUIManager.XUI_isNew,factory.createAttString(isNew));
		
		this.events1.add(dto);
		
	}

	public void update(String xid, String class1, boolean isNew) {
		String type = class1;
		
		XEIDTO dto = factory.create(XUIManager.XUI_UPDATES);
		
		dto.add(XUIManager.XUI_id,factory.createAttString(xid));
		dto.add(XUIManager.XUI_Type,factory.createAttString(type));
		dto.add(XUIManager.XUI_isNew,factory.createAttString(isNew));
		
		this.events1.add(dto);
		
	}
	
	public void update(String xid, String class1, String what,String value) {
		
		XEIDTO dto = factory.create(XUIManager.XUI_UPDATES);
		
		dto.add(XUIManager.XUI_id,factory.createAttString(xid));
		dto.add(XUIManager.XUI_Type,factory.createAttString(class1));
		dto.add(XUIManager.XUI_isNew,factory.createAttString(false));
		dto.add(XUIManager.XUI_update_name,factory.createAttString(what));
		dto.add(XUIManager.XUI_update_value, factory.createAttString(value));

		this.events1.add(dto);
		
	}

	
	public void update(String xid, Class class1, String what,String value) {
		String type = class1.getSimpleName();
		
		XEIDTO dto = factory.create(XUIManager.XUI_UPDATES);
		
		dto.add(XUIManager.XUI_id,factory.createAttString(xid));
		dto.add(XUIManager.XUI_Type,factory.createAttString(type));
		dto.add(XUIManager.XUI_isNew,factory.createAttString(false));
		dto.add(XUIManager.XUI_update_name,factory.createAttString(what));
		
		dto.add(XUIManager.XUI_update_value, factory.createAttString(value));
		this.events1.add(dto);
		
	}


	
}
