package com.labs.xe.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.labs.xe.client.dto.XEIATT;

public class XUIHTML extends XUIBase {
	
	HTML html;
	
	public XUIHTML() {
		html = new HTML();
		setWidget(html);
	}
	
	
	public void setHTML(String html1) {
		html.setHTML(html1);
	}
	
	
	@Override
	public void update(String name, XEIATT value) {
		
		if (name.equalsIgnoreCase("HTML")){
			this.setHTML("" +value.getValue());
		}
		
	}
	

}
