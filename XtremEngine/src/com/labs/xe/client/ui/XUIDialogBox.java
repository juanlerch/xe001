package com.labs.xe.client.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.labs.xe.client.admin.XUIManager;
import com.labs.xe.client.dto.XEIATT;

public class XUIDialogBox extends XUIBase{

    public   DialogBox dialogBox = new DialogBox();

    XUIDockPanel dockPanel ;
    
    Anchor closeAnchor;

    public XUIDialogBox() {
		this.dockPanel = new XUIDockPanel();
		dialogBox.setModal(false);
		
		XUIManager.getInstance().register(dialogBox);
		
		dialogBox.setAnimationEnabled(false);
		dialogBox.ensureDebugId("cwDialogBox");


		dialogBox.setWidget(dockPanel.getWidget());


	}
    
	public XUIDialogBox (String title,XUIDockPanel dockPanel) {
		dialogBox.setModal(false);
		this.dockPanel=dockPanel;
		
		XUIManager.getInstance().register(dialogBox);
		
		/// dialogBox.setGlassEnabled(true);
		

		dialogBox.setAnimationEnabled(false);
		dialogBox.ensureDebugId("cwDialogBox");
		//dialogBox.setText(title);
		// dialogBox.setHeight("100px");
		


		dialogBox.setWidget(dockPanel.getWidget());

		addCloseX(title);

		
	}
	
	
	public void addCloseX(String title){
			
		    closeAnchor = new Anchor("x");

	        FlexTable captionLayoutTable = new FlexTable();
	        captionLayoutTable.setWidth("100%");
	        captionLayoutTable.setText(0, 0, title);
	        captionLayoutTable.setWidget(0, 1, closeAnchor);
	        captionLayoutTable.getCellFormatter().setHorizontalAlignment(0, 1,
	                HasHorizontalAlignment.HorizontalAlignmentConstant.endOf(Direction.LTR));

	        HTML caption = (HTML) dialogBox.getCaption();
	        caption.getElement().removeAllChildren();
	        caption.getElement().appendChild(captionLayoutTable.getElement());

	        caption.addClickHandler(new ClickHandler() {
	            @Override
	            public void onClick(ClickEvent event) {
	                EventTarget target = event.getNativeEvent().getEventTarget();
	                Element targetElement = (Element) target.cast();

	                if (targetElement == closeAnchor.getElement()) {
	                    closeAnchor.fireEvent(event);
	                }
	            }
	        });

	        if (true) {
	        	closeAnchor.addClickHandler(new ClickHandler() {
	                @Override
	                public void onClick(ClickEvent event) {
	                	dialogBox.hide();
	                }
	            });
	        }
	}
	
	public void show(){
		this.dialogBox.show();
	}

	@Override
	public void update(String attname, XEIATT value) {
		if ("caption".equalsIgnoreCase(attname)){
			addCloseX("" + value.getValue());
		}
		else{
			this.dockPanel.update(attname, value);
		}
		
	}
	  	
	
}
