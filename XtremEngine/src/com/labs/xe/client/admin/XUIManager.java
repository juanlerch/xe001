package com.labs.xe.client.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.labs.xe.client.GreetingServiceAsync;
import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.client.ui.XUIBase;
import com.labs.xe.client.ui.XUIDockPanel;
import com.labs.xe.client.ui.XUIFactory;
import com.labs.xe.shared.Xonst;



public class XUIManager implements ClickHandler{

	
	private static GreetingServiceAsync service;
	private static XUIManager instance;
	public static String XUI_UPDATES = "XUI_UPDATES";
	public static String XUI_id = "XUI_xid";
	public static String XUI_Type = "XUI_xType";
	public static String XUI_isNew = "XUI_isNew";
	public static final String XUI_update_name  = "XUI_update_name";
	public static final String XUI_update_value = "XUI_update_value";
	
	long count = 1;
	Map<String,XUIBase> components= new HashMap<String,XUIBase>();
	
	XUIBase root; // root of the ui. first component created
	XUIBase view; // cursor to current component.
	TemplateBox templateBox ;
	XECmdLine  cmdLine;
	
	XEDTOFactory dtoFactory = new XEDTOFactory();
	
	private XUIManager() {
		

	}
	
	public static XUIManager getNewInstance() {
		instance = new XUIManager();
		return instance;
	}
	
	public static XUIManager getInstance() {
		if (instance==null) instance = new XUIManager();
		return instance;
	}
	
	public static GreetingServiceAsync getService() {
		return service;
	}
	
	
	public void init(GreetingServiceAsync greetingService,XUIDockPanel xui){
		this.root = null;
		this.view = null;
		
		service =  greetingService;
		
		
		XEIDTO request = dtoFactory.create(Xonst.REQUEST_TYPE_RUN_SAVED_SCRIPT);
		request.add(Xonst.saved_script_name, dtoFactory.createAttString(Xonst.INIT_SCRIPT_PATH));
		
		service.request(request ,new AsyncCallback<XEIDTO>() {
			
			@Override
			public void onSuccess(XEIDTO result) {
				uiUpdates(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				XUIConsole.show(caught);
				
			}
		});
		
		templateBox = new TemplateBox(greetingService);
		
		templateBox.show();
		
		XUIConsole.show("Hello");
		cmdLine = new XECmdLine(service);
		cmdLine.show();
		this.components.put("c_"+ count++, xui);
		
		
	}
	
	Map<Element,Widget> elementToWidgwet = new HashMap<Element,Widget>();
	
	DialogBox top = null;
@Override
public void onClick(ClickEvent event) {
	
	EventTarget ctarget = event.getNativeEvent().getCurrentEventTarget();
	EventTarget target = event.getNativeEvent().getEventTarget();
	
	target.toSource();
	
	Element e = (Element) target.cast();
	Element ce = (Element) ctarget.cast();

	Widget w =  elementToWidgwet.get(ctarget);
	if (w!=null ){
		if(w instanceof DialogBox){
			DialogBox db=(DialogBox) w;
			if (db != top //&&db!=XUIConsole.getInstance().getDialogBox().getDialogBox()
					){
				top=db;
				top.hide();
				top.show();
			}
		}
		
	}
	
	XUIConsole.show("click on:" +  ":"+ ctarget + ":"   + target);
	XUIConsole.show("element:" +  ":"+ e);
	XUIConsole.show("Class:" +  ":"+ e.getClassName() );
	XUIConsole.show("Style:" +  ":"+ e.getStyle());
	XUIConsole.show("Style:" +  ":"+ e.getStyle().getListStyleType());
	
}

public XUIBase getComponents(String xuid) {
	return components.get(xuid);
}

	public void register(Widget w){
		elementToWidgwet.put(w.getElement(), w);
		w.addDomHandler(this, ClickEvent.getType());
		
	}

	public void uiUpdates(XEIDTO dto){
		
		if (dto==null) return ;
		
		List<XEIDTO> updates = dto.getRel(XUIManager.XUI_UPDATES);
		
		if (updates!=null){
				for (XEIDTO d : updates){
		
					XEIATT axuid= d.get(XUIManager.XUI_id);
					XEIATT atype = d.get(XUIManager.XUI_Type);
					XEIATT aisNew = d.get(XUIManager.XUI_isNew);
					String xuid = (String) axuid.getValue();
					String type = (String) atype.getValue();
					String isNew = (String) aisNew.getValue();
					XUIBase  o =null;
					//if isNew
					if ("true".equalsIgnoreCase(isNew)){
						o = XUIFactory.create(type);
						o.setXid(xuid);
						this.components.put(xuid, o);
						if (root==null) //first component go to GWT Root 
						{
							root=o;
							view=o;
							RootPanel.get("xeMainDiv").clear();
							RootPanel.get("xeMainDiv").add(o.getWidget());;		
						}
					}
					else // is an update
					{
						o = this.components.get(xuid);
						if (o!=null) {
							XEIATT name  = d.get(XUIManager.XUI_update_name); 
							XEIATT value = d.get(XUIManager.XUI_update_value);
							if (name!=null){
								String sname = (String) name.getValue();
								o.update(sname,value);
							}
						}
					}
		
				}
		}
	}
	
	
	
}
