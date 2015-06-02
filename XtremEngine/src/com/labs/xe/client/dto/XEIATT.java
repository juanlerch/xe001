package com.labs.xe.client.dto;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface XEIATT<T> extends IsSerializable,Serializable{

	    public T getValue();
	    public void setValue(T t);


	}
	