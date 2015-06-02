package com.labs.xe.server.dsl.ui;

import com.labs.xe.client.ui.XUIBase;
import com.labs.xe.client.ui.XUIButton;
import com.labs.xe.client.ui.XUIDockPanel;
import com.labs.xe.shared.Xonst;

import groovy.lang.GroovyShell;

public class Panel extends Base {

	XUI ui;
	
	public Panel(XUI ui) {
		this.ui  = ui;
		this.xuid = ui.getNextId();
		ui.update(this.xuid,XUIDockPanel.class,true);
	}
	
	

	
}
