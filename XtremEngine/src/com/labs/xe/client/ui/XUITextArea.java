package com.labs.xe.client.ui;

import com.google.gwt.user.client.ui.TextArea;

public class XUITextArea {
	
	TextArea widget = new TextArea();
	
	
	public XUITextArea() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getText() {
		return widget.getValue();
	}


	public TextArea getWidget() {
		return widget;
	}


	public void setWidget(TextArea widget) {
		this.widget = widget;
	}


	public void setText(String text) {
		this.widget.setValue(text);
		
	}
	
	
	
	

}
