package com.labs.xe.server;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;


public class XUtil {


	
	public void install(){
		
		XDB xdb = new XDB();
		XDBEntity version001 = xdb.get("XVersion","0.0.1");
		if (version001==null){
			this.configure();
		}
		
	}

	private void configure() {
		    XDB xdb = new XDB();
		    xdb.configure();

		
		
	}
	
	
}
