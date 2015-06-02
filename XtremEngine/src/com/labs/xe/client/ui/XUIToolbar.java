package com.labs.xe.client.ui;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class XUIToolbar {
	
	HorizontalPanel widget = new HorizontalPanel();
	
	public XUIToolbar() {
		// TODO Auto-generated constructor stub
	}
	
	
	public XUIButton  addButton(XUIEventHandler handler,String title,String value){
		XUIButton button = new XUIButton(title, value);
		button.addEventHandler(handler) ;
		widget.add(button.getWidget());
		return button;
	} 
	
	
	public XUITextArea  addTextArea(){
		XUITextArea textArea= new XUITextArea();
		widget.add(textArea.getWidget());
		return textArea;
	} 

	public HorizontalPanel getWidget() {
		return widget;
	}

}
