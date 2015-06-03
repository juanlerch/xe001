package com.labs.xe.server.dsl.ui;

import com.labs.xe.client.ui.XUIButton;
import com.labs.xe.client.ui.XUITextArea;
import com.labs.xe.shared.Xonst;

import groovy.lang.GroovyShell;

public class TextArea extends Base {

	XUI ui;
	String text;
	
	public TextArea(XUI ui) {
		this.ui  = ui;
		this.xuid = ui.getNextId();
		ui.update(this.xuid,XUITextArea.class,true);
	}

	public void setText(String text){
		this.text=text;
		ui.update(this.xuid,XUITextArea.class,"text",text);
		
	}
	 
	
	

	
	
}
