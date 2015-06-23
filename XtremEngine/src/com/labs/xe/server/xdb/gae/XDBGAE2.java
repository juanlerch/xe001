package com.labs.xe.server.xdb.gae;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.server.xdb.XDB;

public class XDBGAE2 implements XDB {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();


	public XEIDTO save   (XEIDTO data){
		Entity entity  = null;
		
		String type = data.getName();
		String xdbid = data.getValueAsString("xdbid");  
		boolean isUpdate = false;
		if (xdbid != null){
			isUpdate = true;
		}  
		
		if (isUpdate){
			Key key = KeyFactory.createKey(type, xdbid);
			try {
				entity = datastore.get(key);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			//todo
		}
		
		for (String key:data.getEntryKey()){
			//todo persistir los atributos
		}
		for (String key:data.getEntryKey()){
			//todo persistir las relaciones
		}
		
		return data;
	}
	
	public XEIDTO delete (XEIDTO data){
		return data;
	}
	
	public XEIDTO query  (XEIDTO data){
		return data;
	}
	
}

