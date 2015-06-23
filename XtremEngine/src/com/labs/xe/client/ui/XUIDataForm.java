package com.labs.xe.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.labs.xe.client.GreetingService;
import com.labs.xe.client.admin.XUIConsole;
import com.labs.xe.client.admin.XUIManager;
import com.labs.xe.client.dto.XEDTO;
import com.labs.xe.client.dto.XEDTOConstants;
import com.labs.xe.client.dto.XEDTOUtil;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;

public class XUIDataForm extends DecoratorPanel implements ClickHandler{

	FlexTable flexTable  = new FlexTable();
	int selectedRow=-1;
	XEIDTO dto;
	Button save ;
	public XUIDataForm () {
		
		int col=0;
		this.setTitle("Title");
		this.add(flexTable);
	}

	public void show(XEIDTO dto){
		this.selectedRow=-1;
		this.dto=dto;
		flexTable.clear();
		int row=0;
		
		
		for (String t:dto.getEntryKey()){
			flexTable.setWidget(row,0, new HTML(t));
			TextBox textBox = new TextBox();
			Object o =dto.get(t);
			String  s = "" + o;
			textBox.setValue(s);
			flexTable.setWidget(row,1, textBox);
			row++;
		}
		
		save = new Button("Save");
		flexTable.setWidget(row+1, 2, save);
		
		save.addClickHandler(this);

	}
	@Override
	public void onClick(ClickEvent event) {
		int r=0;

		//Cambiar los datos en el dto de origen
		for (String t:dto.getEntryKey()){
			TextBox tdata = (TextBox) flexTable.getWidget(r,1);
			XEIATT att = dto.get(t);
			att.setValue(tdata.getText());
			r++;
		}
		
		//todo:save.setText("Saving...");
		/*XUIManager.getService().save(dto, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				save.setText("Saved");
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				save.setText("Not Saved");
				XUIConsole.show(caught.getMessage());
			}
		});*/
		
	}
	
	
}
