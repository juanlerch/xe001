package com.labs.xe.client.admin;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.labs.xe.client.GreetingServiceAsync;
import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.ui.XUIDialogBox;
import com.labs.xe.client.ui.XUIDockPanel;
import com.labs.xe.client.ui.XUIEvent;
import com.labs.xe.client.ui.XUIEventHandler;
import com.labs.xe.client.ui.XUIFactory;
import com.labs.xe.client.ui.XUIHTML;
import com.labs.xe.client.ui.XUITextArea;
import com.labs.xe.client.ui.XUIToolbar;
import com.labs.xe.shared.Xonst;
 
public class XECmdLine implements XUIEventHandler{
	XUIDialogBox dialogBox; 
	XUIDockPanel panel  = new XUIDockPanel();
	XUIToolbar   tool = new XUIToolbar();
	GreetingServiceAsync service;
	XUITextArea tarea =null ;
	XUIHTML html = new XUIHTML(); 
	public XECmdLine(GreetingServiceAsync service) {
		this.service=service;
		this.dialogBox = new XUIDialogBox("Command", panel);
		panel.add(tool.getWidget(), DockPanel.NORTH);
		tarea = new XUITextArea();//tool.addTextArea();
		panel.add(tarea.getWidget());
		tool.addButton(this, "Run!", "RunScript");
		tool.addButton(this, "Save as...", "saveAs");
		tool.addButton(this, "New console!", "newConsole");
		panel.add(html.getWidget(),DockPanel.SOUTH);
		tarea.setText("1 + 2");
	} 
	
	@Override
	public void notify(XUIEvent event) {
		if (event.getValue().equalsIgnoreCase("RunScript")){
			this.runScript(event);
		}
		if (event.getValue().equalsIgnoreCase("newConsole")){
			XECmdLine newCLine = new XECmdLine(service);
			
			newCLine.show();
			
		}
		
	}
	
	

	private void createButton(XUIEvent event) {
		XEDTOFactory factory = new XEDTOFactory();
		XEIDTO dto = factory.create("className");
		dto.add("className", factory.createAttString("button"));
	}


	private void runScript(XUIEvent event) {
		XEDTOFactory factory = new XEDTOFactory();
		XEIDTO dto = factory.create(Xonst.SCRIPT_XLINE);
		
		dto.add(Xonst.SCRIPT_script,factory.createAttString(tarea.getText()));
		
		
		XEIATT  g = factory.createAttDTO( XUIManager.getInstance().getGlobals());
		dto.add(Xonst.XE_GLOBALS,g);
		
		service.request(dto, new AsyncCallback<XEIDTO>() {
			
			@Override
			public void onSuccess(XEIDTO result) {
				String shtml = "" +result.get(Xonst.SCRIPT_result).getValue();
				html.setHTML(shtml);
				XUIManager.getInstance().uiUpdates(result);
				XUIManager.getInstance().globalsUpdates(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {

				String shtml = "" +caught.getMessage();
				html.setHTML(shtml);
			}
		});
		
	}

	public void show() {
		this.dialogBox.show();
	}
	
	
	
}
