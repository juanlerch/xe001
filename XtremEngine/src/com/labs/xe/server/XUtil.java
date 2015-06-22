package com.labs.xe.server;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.labs.xe.server.xdb.gae.XDBGae;
import com.labs.xe.server.xdb.gae.XDBEntity;


public class XUtil implements Serializable{


	
	public void install(){
		
		XDBGae xdb = new XDBGae();
		XDBEntity version001 = xdb.get("XVersion","0.0.1");
		if (version001==null){
			this.configure();
		}
		
		XDataSamplePeople sample1= new XDataSamplePeople();
		sample1.configure();
		
	}

	private void configure() {
		    XDBGae xdb = new XDBGae();
		    xdb.configure();

		
		
	}
	
	
	
	
}
