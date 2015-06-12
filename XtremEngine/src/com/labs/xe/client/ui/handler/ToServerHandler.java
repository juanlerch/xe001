package com.labs.xe.client.ui.handler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.labs.xe.client.GreetingServiceAsync;
import com.labs.xe.client.admin.XUIConsole;
import com.labs.xe.client.admin.XUIManager;
import com.labs.xe.client.dto.XEATT_XDTO;
import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.ui.XUIBase;
import com.labs.xe.client.ui.XUIEvent;
import com.labs.xe.client.ui.XUIEventHandler;
import com.labs.xe.server.dsl.ui.XUI;
import com.labs.xe.shared.Xonst;

public class ToServerHandler implements XUIEventHandler{
	
	@Override
	public void notify(XUIEvent event) {
		GreetingServiceAsync service = XUIManager.getService();
		
	//	String name = event.getEvent();
		XUIBase base =  event.getObject();
		
		XEDTOFactory factory = new XEDTOFactory();
		XEIDTO dto = factory.create(Xonst.SCRIPT_DSL_SESSION);
		
		dto.add(Xonst.SCRIPT_xui,factory.createAttString(base.getXID()));
		XEIATT  g = factory.createAttDTO( XUIManager.getInstance().getGlobals());
		dto.add(Xonst.XE_GLOBALS,g);
		
		
		
		
		service.request(dto, new AsyncCallback<XEIDTO>() {
			
			@Override
			public void onSuccess(XEIDTO result) {
				XUIManager.getInstance().uiUpdates(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				XUIConsole.show(caught);
			}
		});
		
		
	}

}
