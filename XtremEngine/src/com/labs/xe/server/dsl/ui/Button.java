package com.labs.xe.server.dsl.ui;

import com.labs.xe.client.ui.XUIButton;

public class Button extends Base {

	
	String onClick;
	String caption;
	public Button() {
	
	}

	public void setCaption(String caption){
		this.caption=caption;
	}
	 
	
	public void setOnClick(String onclick){
		this.onClick = onclick;
	}

	
	
}
