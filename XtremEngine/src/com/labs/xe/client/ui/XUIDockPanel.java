package com.labs.xe.client.ui;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.DockPanel.DockLayoutConstant;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.labs.xe.client.admin.XUIConsole;
import com.labs.xe.client.admin.XUIManager;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.server.dsl.ui.XUI;

public class XUIDockPanel extends XUIBase  {

	boolean isSplit=false;
	
	VerticalPanel  mainPanel; 
	SplitLayoutPanel splitPanel ;
	//ScrollPanel centerScroll = new ScrollPanel();
	
	
	FlowPanel north = new FlowPanel();
	FlowPanel south = new FlowPanel();
	FlowPanel centerfpanel = new FlowPanel();
	VerticalPanel   west   = new VerticalPanel();
	VerticalPanel   east   = new VerticalPanel();
	VerticalPanel   center = new VerticalPanel();
	
	
	
	public Widget getWidget(){
		if (isSplit) return splitPanel;
		else return mainPanel;
	} 
	

	
	
	public void add(Widget  w, DockLayoutConstant direction) {
		w.getElement().getStyle().setProperty("float", "left");
		if (mainPanel!=null) {
			if(DockPanel.NORTH == direction){
				  north.add(w);	
				}
				else if(DockPanel.EAST== direction){
				  east.add(w);	
				}	
				else if(DockPanel.WEST== direction){
					  west.add(w);	
					}	
				else if(DockPanel.SOUTH== direction){
					  south.add(w);	
					}	
				else{
					center.add(w);
				}
		}
		else{ 
			if(DockPanel.NORTH == direction){
			  splitPanel.addNorth(w, 50);	
			}
			else if(DockPanel.EAST== direction){
			  splitPanel.addEast(w, 100);	
			}	
			else if(DockPanel.WEST== direction){
				  splitPanel.addWest(w, 100);	
				}	
			else if(DockPanel.SOUTH== direction){
				  splitPanel.addSouth(w, 50);	
				}	
		}
	}
		
	    
	    
		public void add(Widget  w) {
			
			w.getElement().getStyle().setProperty("float", "left");
			if (mainPanel!=null) {
				center.add(w);
			}
			else { 
				splitPanel.add(w);	
			}	
			
	}
	
	
	
	public XUIDockPanel() {
		
		north.getElement().getStyle().setProperty("float", "left");
		east.getElement().getStyle().setProperty("float", "left");
		center.getElement().getStyle().setProperty("float", "left");
		west.getElement().getStyle().setProperty("float", "left");
		south.getElement().getStyle().setProperty("float", "left");
		
		if (isSplit) splitPanel = new SplitLayoutPanel();
		else {mainPanel = new VerticalPanel();
			mainPanel.getElement().getStyle().setProperty("float", "left");
			 // mainPanel.setWidth("100%");
			  mainPanel.add(north);//,DockPanel.NORTH);
			  //centerfpanel.add(new Button());
			  //centerfpanel.add(new Button());
			  //centerfpanel.add(new HTML("center"));
			  //centerfpanel.add(new HTML("center"));
			  
			  centerfpanel.add(east);
			  centerfpanel.add(center);
			  centerfpanel.add(west);

			  mainPanel.add(centerfpanel);
			  mainPanel.add(south);//,DockPanel.SOUTH);
		      
			}
		
	}

	@Override
	public void update(String attname, XEIATT value) {
		String svalue=value.toString();
		XUIBase a = XUIManager.getInstance().getComponents(svalue);
		if ("north".equalsIgnoreCase(attname)){
			this.add(a.getWidget(), DockPanel.NORTH);
		}
		else if ("south".equalsIgnoreCase(attname)){
			this.add(a.getWidget(), DockPanel.SOUTH);
		}
		else if ("east".equalsIgnoreCase(attname)){
			this.add(a.getWidget(), DockPanel.EAST);
		}
		else if ("west".equalsIgnoreCase(attname)){
			this.add(a.getWidget(), DockPanel.WEST);
		}
		else if ("styleName".equalsIgnoreCase(attname)){
			this.mainPanel.setStyleName(svalue);
		}
		else{
		    this.add(a.getWidget());	 
		}
		
	}
	
	
}
