package com.labs.xe.client.dto;

import java.util.ArrayList;
import java.util.List;

public class XEATTListOfString implements XEIATT <List<String>>{
	
	List<String> value = new ArrayList<String>();
	@Override
	public List<String> getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	
	public void setValue(List<String>  value) {
		this.value = value;
	}
	
	
	public XEATTListOfString() {


	}

	
	@Override
	public String toString(int deep) {
		return value.toString();
	}
	
}
