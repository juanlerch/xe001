
package com.labs.xe.client.ui;

import java.util.ArrayList;
import java.util.List;



import java.util.Set;

import org.apache.xalan.lib.sql.XConnection;

import com.google.gwt.thirdparty.javascript.rhino.head.ast.FunctionNode.Form;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.labs.xe.client.XtremEngine;
import com.labs.xe.client.admin.XUIConsole;
import com.labs.xe.client.admin.XUIManager;
import com.labs.xe.client.dto.XEDTO;
import com.labs.xe.client.dto.XEDTOConstants;
import com.labs.xe.client.dto.XEDTOUtil;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;


public class XUIDataTable {
	
	XUIDataForm   defaulDataForm;
	
	Widget widget ;
	
	public Widget getWidget() {
		return widget;
	}

	final XEIDTO dto;

	public XUIDataTable(XEIDTO dtos) {
		this.dto =dtos;
		
		final CellTable<XEIDTO> table = new CellTable<XEIDTO>();
		
		
		VerticalPanel vpanel = new VerticalPanel();
		widget=vpanel;
		
		SimplePager pager = new SimplePager();
	    pager.setDisplay(table);
		vpanel.add(table);
		vpanel.add(pager);

		
		List<TextColumn<String>> columns  = new ArrayList<TextColumn<String>>();
		int colIndex=0;
		XUIConsole.show(dtos);
		
		List<String> columnsNames = XEDTOUtil.getAttAsStringList(dtos, XEDTOConstants.HEADER);
		
	    for (String colName : columnsNames){
	    	// XUIConsole.show(colName);	  
		     final String colName1=colName;
		     TextColumn<XEIDTO> txtcol = new TextColumn<XEIDTO>() {
			      @Override
			      public String getValue(XEIDTO data) {
			    	  String s = "" + data.get(colName1);
			    	  return s;
			        
			      }
		    };
		    
		    table.addColumn(txtcol, colName);  
		    
	    }
	    
	 // Create a data provider.
	    ListDataProvider<XEIDTO> dataProvider = new ListDataProvider<XEIDTO>();

	    // Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    //dataProvider.setList(dtos.getData());
	  // System.out.println("XXXXXXXXXXXXXXXX");
	  // int x=1;
	    
	    /*for (XEIDTO o:dtos.getRel(XEDTOConstants.QUERY_DATA)){
	    	XUIConsole.show("Cargando Tabla" + x++); 
	    	dataProvider.getList().add(o);
	    }*/
	    
	    dataProvider.getList().addAll(dtos.getRel(XEDTOConstants.QUERY_DATA));
	    
	    
	    table.setHeight("100%");
	    
	    table.addCellPreviewHandler(new CellPreviewEvent.Handler<XEIDTO>() {

	        @Override
	        public void onCellPreview(CellPreviewEvent<XEIDTO> event) {
	            int row = event.getIndex();
	            int column = event.getColumn();
	        	
	            if ("focus".equals(event.getNativeEvent().getType())) {
	               //..
	            }
	            if ("blur".equals(event.getNativeEvent().getType())) {
	                //...
	            }
	            if ("mousedown".equals(event.getNativeEvent().getType())) {
	            	XUIConsole.show("onCellPreview:" + row);
					showDataForm(row);
	            }
	            if ("mouseover".equals(event.getNativeEvent().getType())) {
	                //..
	            }
	        }

	    });
		
	    final SingleSelectionModel<XEIDTO> selectionModel = new SingleSelectionModel<XEIDTO>();
	  //final MultiSelectionModel<Contact> selectionModel = new MultiSelectionModel<Contact>();
	    table.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

	        @Override
	        public void onSelectionChange(SelectionChangeEvent event) {
	        	int row = table.getKeyboardSelectedRow();
	        	 
	        	try {
	        		if (true) return; //todo:don't work
	        		XUIConsole.show("onSelectionChange 1");
	        		XEIDTO dto = selectionModel.getSelectedObject();
	        		XUIConsole.show(dto);
		        	XUIConsole.show("onSelectionChange 2");
	        	}
	        	catch (Exception e) {
	        		XUIConsole.show(e.getMessage());
				}
	        	
	           	//showDataForm(row);
	        }
	    });
	    
	    
	    // Connect the table to the data provider.
	    dataProvider.addDataDisplay(table);
	    
		
		
	}
	
	public void showDataForm(int row){
		XUIConsole.show("showDataForm:"+row);
		if (this.defaulDataForm == null){
			
			XUIDockPanel panel = new XUIDockPanel();
			
			XUIDataForm  form = new XUIDataForm();
			defaulDataForm = form;
			panel.add(form);
			
			XUIDialogBox dialog = new XUIDialogBox("Entity:",panel);
			//dialog.add(panel.getWidget());
			dialog.show();
		}
		
		
		XEIDTO d =(XEIDTO) dto.getRel(XEDTOConstants.QUERY_DATA).get(row);
		defaulDataForm.show(d);
		

	}

	public void showNewDataForm(String type){
		XUIConsole.show("newDataForm:"+type);
		if (this.defaulDataForm == null){
			
			XUIDockPanel panel = new XUIDockPanel();
			
			XUIDataForm  form = new XUIDataForm();
			defaulDataForm = form;
			panel.add(form);
			
			XUIDialogBox dialog = new XUIDialogBox("Entity:",panel);
			//dialog.add(panel.getWidget());
			dialog.show();
		}
		
		XUIManager.getService().createInstance(type, new AsyncCallback<XEIDTO>() {
				@Override
				public void onSuccess(XEIDTO result) {
					defaulDataForm.show(result);
				}
				@Override
				public void onFailure(Throwable caught) {
					XUIConsole.show(caught);
				}
			});
		}
	




}
