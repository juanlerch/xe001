package com.labs.xe.client.admin;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.Splittable;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.ui.XUIDialogBox;
import com.labs.xe.client.ui.XUIDockPanel;

public class XUIConsole {

	public static XUIConsole instance;
	
	public static XUIConsole getInstance() {
		if (instance== null) {
			instance = new XUIConsole();
			instance.show();
		}
		return instance;
	}
	
    public   XUIDialogBox dialogBox ;
    public VerticalPanel dialogContents;

    public XUIDialogBox getDialogBox() {
		return dialogBox;
	}
    
    public static void show(XEIDTO dto){
    	
    	XUIConsole console = getInstance();
    	Splittable s;
    	String br = "<BR>";
    	String requestData = "dto is null";
    	
    	if (dto != null){
    		requestData =dto.getName() + br;	
    	
    	for(String key : dto.getEntryKey()){
    		requestData += key + ":" + dto.get(key);
    	}
    	
    	for(String key : dto.getRelEntryKey()){
    		requestData += key + ":" + dto.getRel(key).size() + " dtos";
    	}
    	
    	}
    	
    	HTML html = new HTML(requestData);
    	console.add(html);
        
    }
    
    public static void show(String message){
    	XUIConsole console = getInstance();
    	HTML html = new HTML(message);
    	console.add(html);
    	
    }
    
	private XUIConsole () {
		/// dialogBox.setGlassEnabled(true);
		

		dialogContents = new VerticalPanel();
	    dialogContents.setSpacing(4);

	    
		XUIDockPanel panel = new XUIDockPanel();
		panel.add(dialogContents);
		
		dialogBox = new XUIDialogBox("Xonsole",panel);
	//	dialogBox.setModal(false);
	//	dialogBox.getDialogBox().setAnimationEnabled(false);
	//	dialogBox.getDialogBox().ensureDebugId("cwDialogBox");

	    
	    
			    // Add an image to the dialog
/*			    Image image = new Image(Showcase.images.jimmy());
			    dialogContents.add(image);
			    dialogContents.setCellHorizontalAlignment(
			        image, HasHorizontalAlignment.ALIGN_CENTER);
*/

	}
	
	public void show(){
		this.dialogBox.show();
	}
	
	  	
	public void add(Widget w){
		
		dialogContents.add(w);
	    // Add some text to the top of the dialog
		dialogContents.setCellHorizontalAlignment(w, HasHorizontalAlignment.ALIGN_CENTER);
		
		//dialogContents.setHeight("500px");
	
	}
	
	
	public static void show(Throwable caught) {
		show(caught.getMessage());
		
	}
	
}
