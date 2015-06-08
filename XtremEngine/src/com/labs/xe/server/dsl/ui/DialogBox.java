package com.labs.xe.server.dsl.ui;

import com.labs.xe.client.ui.XUIButton;
import com.labs.xe.client.ui.XUIDialogBox;

public class DialogBox extends Base {

	XUI ui;
	String caption;

	public DialogBox(XUI ui) {
		this.ui  = ui;
		this.xuid = ui.getNextId();
		ui.update(this.xuid,XUIDialogBox.class,true);
	}

	public void setCaption(String caption){
		this.caption=caption;
		ui.update(this.xuid,XUIButton.class,"caption",caption);
	}
	 
	

	
	
}
