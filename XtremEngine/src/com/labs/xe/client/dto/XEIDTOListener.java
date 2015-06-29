package com.labs.xe.client.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;





import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface XEIDTOListener<T> extends IsSerializable,Serializable{

	void onChangeAtt(String att,XEIATT<T> value);
	void onChangeRel(String att,XEIDTO value);
	
}