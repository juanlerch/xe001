package com.labs.xe.client.ui;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
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
	
	DockPanel dockPanel; 
	SplitLayoutPanel splitPanel ;
	ScrollPanel centerScroll = new ScrollPanel();
	
	
	FlowPanel north = new FlowPanel();
	FlowPanel south = new FlowPanel();
	VerticalPanel   west   = new VerticalPanel();
	VerticalPanel   east   = new VerticalPanel();
	VerticalPanel   center = new VerticalPanel();
	FlowPanel centerfpanel = new FlowPanel();
	
	
	public Widget getWidget(){
		if (isSplit) return splitPanel;
		else return dockPanel;
	} 
	

	
	
	public void add(Widget  w, DockLayoutConstant direction) {
		if (dockPanel!=null) {
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
				  splitPanel.addWest(w, 50);	
				}	
		}
	}
		
	    
	    
		public void add(Widget  w) {
			if (dockPanel!=null) {
				
				center.add(w);
				//centerVpanel.setSize("100%", "100%");
				/*dockPanel.addHandler(new ResizeHandler() {
					
					@Override
					public void onResize(ResizeEvent event) {
						//XUIConsole.show("resize" + dockPanel.getOffsetHeight());
						//if (dockPanel.getOffsetHeight()>500) scroller.setHeight("500px");
						
					}
				}, ResizeEvent.getType());*/
				
			}
			else { 
				splitPanel.add(w);	
				}	
			
	}
	
	
	
	public XUIDockPanel() {
		if (isSplit) splitPanel = new SplitLayoutPanel();
		else {dockPanel = new DockPanel();
			  centerfpanel.setWidth("100%");
			  dockPanel.add(north,DockPanel.NORTH);
			  dockPanel.add(south,DockPanel.SOUTH);
			  dockPanel.add(new HTML("west"),DockPanel.WEST);
			  dockPanel.add(new HTML("east"),DockPanel.EAST);

			  
			  centerScroll.setWidget(this.center);

			  centerfpanel.add(east);
			  centerfpanel.add(center);
			  centerfpanel.add(west);

			  
			  dockPanel.add(centerfpanel,DockPanel.CENTER);

		      
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
			this.dockPanel.setStyleName(svalue);
		}
		else{
		    this.add(a.getWidget());	 
		}
		
	}
	
	
}
