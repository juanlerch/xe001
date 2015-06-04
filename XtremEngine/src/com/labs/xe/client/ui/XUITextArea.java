package com.labs.xe.client.ui;

import com.google.gwt.user.client.ui.TextArea;
import com.labs.xe.client.dto.XEIATT;

public class XUITextArea extends XUIBase {
	
	TextArea textArea = new TextArea();
	
	
	public XUITextArea() {
		super.setWidget(textArea);
		textArea.setWidth("100%");
	}
	
	

	
	
	@Override
	public void update(String name, XEIATT value) {
		if ("text".equalsIgnoreCase(name)){
			String svalue = (String) value.getValue();
			this.textArea.setValue(svalue);
		}
		
	}





	public void setText(String svalue) {
		this.textArea.setValue(svalue);
	}





	public Object getText() {
		
		return this.textArea.getText();
	}	
	

}
