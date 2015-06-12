package com.labs.xe.client.ui;

import com.labs.xe.client.ui.handler.ToServerHandler;

public class XUIFactory {

	
	static public XUIBase create (String type) {
		
		if (type.equalsIgnoreCase(XUIButton.class.getSimpleName())){
			XUIButton b=  new XUIButton();
			b.addEventHandler(new ToServerHandler());
			return b;
		}
		if (type.equalsIgnoreCase(XUIDockPanel.class.getSimpleName())){
			XUIDockPanel b=  new XUIDockPanel();
			
			return b;
		}
		if (type.equalsIgnoreCase(XUIDialogBox.class.getSimpleName())){
			XUIDialogBox b=  new XUIDialogBox();
			b.show();
			return b;
		}
		if (type.equalsIgnoreCase(XUITextArea.class.getSimpleName())){
			XUITextArea b=  new XUITextArea();
			return b;
		}
		if (type.equalsIgnoreCase(XUIHTML.class.getSimpleName())){
			XUIHTML b=  new XUIHTML();
			return b;
		}
		else {
			XUIHTML html = new XUIHTML();
			html.setHTML("can't create : " + type);
			return html; 

		}
		
		
	}
	
}
