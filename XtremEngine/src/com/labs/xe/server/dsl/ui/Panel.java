package com.labs.xe.server.dsl.ui;

import com.labs.xe.client.ui.XUIDockPanel;

public class Panel extends Base {

	XUI ui;
	
	public Panel(XUI ui) {
		this.ui  = ui;
		this.xuid = ui.getNextId();
		ui.update(this.xuid,XUIDockPanel.class,true);
	}
		
	

	
}
