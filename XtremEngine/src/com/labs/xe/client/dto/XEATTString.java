package com.labs.xe.client.dto;

public class XEATTString implements XEIATT<String>{
	
	String value;
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	
	public void setValue(String value) {
		this.value = value;
	}
	
	
	
@Override
public String toString() {
	return value;
}
	
	
	
	
}
