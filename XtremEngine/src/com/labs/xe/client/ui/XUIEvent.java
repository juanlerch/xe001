package com.labs.xe.client.ui;

public class XUIEvent {

	static String EVENT_TYPE_ON_CLICK = "onClick";
	
	
	String event;
	
	String value;
	
	XUIBase object;
	
	public XUIEvent(String event) {
		this.event=event;
	}
	
	public XUIEvent(String event,String value) {
		this.event=event;
		this.value=value;
	}
	
	public XUIEvent(String event,String value,XUIBase current) {
		this.event=event;
		this.value=value;
		this.object = current;
	}
	
	public String getValue() {
		return value;
	}
	
	
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public XUIBase getObject() {
		return object;
	}

	public void setObject(XUIBase object) {
		this.object = object;
	}
	
	
	
	
	
	
}
