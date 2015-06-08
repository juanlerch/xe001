package com.labs.xe.server.dsl.ui;

import com.labs.xe.client.ui.XUIButton;

public class Button extends Base {

	XUI ui;
	String onClick;
	String caption;
	public Button(XUI ui) {
		this.ui  = ui;
		this.xuid = ui.getNextId();
		ui.update(this.xuid,XUIButton.class,true);
	}

	public void setCaption(String caption){
		this.caption=caption;
		ui.update(this.xuid,XUIButton.class,"caption",caption);
	}
	 
	
	public void setOnClick(String onclick){
		this.onClick = onclick;
	}

	
	
}
