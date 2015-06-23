package com.labs.xe.client.admin;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.labs.xe.client.GreetingServiceAsync;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.ui.XUIDataTable;
import com.labs.xe.client.ui.XUIDialogBox;
import com.labs.xe.client.ui.XUIDockPanel;
import com.labs.xe.client.ui.XUIEvent;
import com.labs.xe.client.ui.XUIEventHandler;
import com.labs.xe.client.ui.XUIToolbar;

public class TemplateBox {

	
	XUIDialogBox dialogBox ;
	XUIDockPanel panel ;
	VerticalPanel vpanel = new VerticalPanel();
	GreetingServiceAsync service;
	
	public TemplateBox(GreetingServiceAsync service) {
		
		this.service=service;
		this.panel = new XUIDockPanel();
		this.dialogBox = new XUIDialogBox("Templates", panel);
		
		panel.add(vpanel);
	}
	
	public void show(){
		 /*
		service.getTemplates(new AsyncCallback<List<String>>() {
			 
			@Override
			public void onSuccess(List<String> result) {
				
				
				
				
				
				for(String s : result){
					String data = s ;
					final HTML html = new HTML(data);
									 
					
					html.addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							final  String type = html.getHTML().toString();
						    service.query(type , new AsyncCallback<XEIDTO>() {
							
							@Override
							public void onSuccess(XEIDTO  result) {
								
								XUIDockPanel panel = new XUIDockPanel();
								
								XUIDialogBox dtd= new XUIDialogBox(type,panel);
								final XUIDataTable t = new XUIDataTable(result);
								XUIToolbar toolbar = new XUIToolbar();
								toolbar.addButton(new XUIEventHandler() {
									@Override
									public void notify(XUIEvent event) {
										t.showNewDataForm(event.getValue());
									}
								}, "New", type);
								
								
								
								panel.add(toolbar.getWidget(),DockPanel.NORTH);
								panel.add(t.getWidget());
								
								
								dtd.show();
							}
							
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}
						});	
							
						}
					});
					
				vpanel.add(html);
				}
				
				//dialogBox.add(vpanel);
				dialogBox.show();
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				XUIConsole.show(caught.getMessage());			}
		});
		
		
		*/
		
	}
	
	  	
	
}
