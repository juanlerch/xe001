package com.labs.xe.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.labs.xe.client.dto.XEIATT;

public class XUIHTML extends XUIBase {
	
	
	
	public XUIHTML() {
		setWidget(new HTML());
	}
	
	
	public void setHTML(String html) {
		HTML html1 = (HTML)this.getWidget();
		html1.setHTML(html);
	}
	
	
	@Override
	public void update(String name, XEIATT value) {
		
		
	}
	

}
