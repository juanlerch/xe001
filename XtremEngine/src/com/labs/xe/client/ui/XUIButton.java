package com.labs.xe.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.ui.handler.ToServerHandler;

public class XUIButton extends XUIBase implements ClickHandler{
	
	Button button ;
	String value;
	
	public XUIButton() {
		init();
	}

	public XUIButton(String title,String value) {
		init();
		button.setText(title);
		this.value = value;
	}

	
	public void init(){
		button = new Button();
		setWidget(button);
		button.addClickHandler(this);
	}
	public void setCaption(String title){
		button.setText(title);
	}
	
	
	
	@Override
	public void onClick(ClickEvent event) {
		if(this.getEventHandler()!=null){
			for(XUIEventHandler h:this.getEventHandler()){
				h.notify(new XUIEvent(XUIEvent.EVENT_TYPE_ON_CLICK, value, this));
			}
		}
	}
	
	@Override
	public void update(String name,XEIATT value) {
		if ("caption".equalsIgnoreCase(name)){
			this.setCaption((String) value.getValue()); 
		}
	}
	
}
