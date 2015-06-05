package com.labs.xe.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.labs.xe.client.admin.XUIManager;
import com.labs.xe.client.ui.XUIDockPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */ 
public class XtremEngine implements EntryPoint {
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */ 
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	
	XUIDockPanel xui = new XUIDockPanel();
	
	public void onModuleLoad() {
		HTML h = new HTML("loading  XE ...");
		RootPanel.get("xeMainDiv").add(h);
		/*xui.add(new HTML("cwDockPanelNorth1()"), DockPanel.NORTH);
		xui.add(new HTML("cwDockPanelSouth1()"), DockPanel.SOUTH);
		xui.add(new HTML("cwDockPanelEast()"), DockPanel.EAST);
		xui.add(new HTML("cwDockPanelWest()"), DockPanel.WEST);
		xui.add(new HTML("cwDockPanelNorth2()"), DockPanel.NORTH);
		xui.add(new HTML(".cwDockPanelSouth2()"), DockPanel.SOUTH);
		xui.add(new HTML("CENTER"));
		*/
		init();
		
		

	}

	private void init() {
		XUIManager.getNewInstance().init(greetingService,xui);
	}
}
