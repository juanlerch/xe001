package com.labs.xe.client.dto;

public class XEATT_XDTO implements XEIATT<XEIDTO> {
	
	XEIDTO value;
	@Override
	public XEIDTO getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	
	public void setValue(XEIDTO value) {
		this.value = value;
	}
	
	
	
@Override
public String toString() {
	return value.toString(3);
}
	@Override
	public String toString(int deep) {
		return value.toString(deep);
	}
	

}
