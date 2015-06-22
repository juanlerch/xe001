package com.labs.xe.server.xdb.gae;

import java.util.ArrayList;
import java.util.List;

public class XDBTemplate {

	String name;
	
	List<String> attributeName = new ArrayList<String>();
	
	String data[] = new String[3];

	
	public int dataIndex(String name){
		int t=0;
		for(String d:data){
			if (d.equalsIgnoreCase(name)) return t;
			t++;
		}
		return -1;
		
	}
	
	public boolean isProperty(String name){
		return attributeName.contains(name);
	}
	
	
	public XDBTemplate(String name) {
		this.name=name;
	}
	
	

	public void addAtribute(String name){
		this.attributeName.add(name);
	} 
	public void setData1(String name){
		data[0]=name;
	}
	
	public void setData2(String name){
		data[1]=name;
	}
	
	public void setData3(String name){
		data[2]=name;
	}
	public List<String> getAttributeName() {
		return attributeName;
	}

	public String getName() {
		return name;
	}
	
	public String[] getData() {
		return data;
	}	
		
	
}
