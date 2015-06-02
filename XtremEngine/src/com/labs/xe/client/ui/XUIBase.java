package com.labs.xe.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.ui.handler.ToServerHandler;

public abstract class XUIBase {

	private Widget widget;
	private String xid;
	
	private List<XUIEventHandler> eventHandler;
	
	public void setWidget(Widget widget) {
		this.widget = widget;
	}
	
	public Widget getWidget(){
		return widget;
	}
	
	public void setXid(String xid) {
		this.xid = xid;
	}
	
	public String getXID(){
		return this.xid;
	}
	
	public XUIBase() {
		// TODO Auto-generated constructor stub
	};
	
	
	public void addEventHandler(XUIEventHandler handler){
		if (eventHandler==null) eventHandler = new ArrayList<XUIEventHandler>();
		eventHandler.add(handler);
	}
	public List<XUIEventHandler> getEventHandler() {
		return eventHandler;
	}
	
	
	abstract  public void update(String name,XEIATT value);
	
	
}
