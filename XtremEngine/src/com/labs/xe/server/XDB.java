package com.labs.xe.server;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class XDB {
	
	
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	 
    
	 XDBEntity get(String type,String id){
		
		Key k = KeyFactory.createKey(type, type+"-"+id);

		try {
			
			Entity e = datastore.get(k);

			XDBEntity xe = new XDBEntity();
			xe.setEntity(e);
			return xe;
		} catch (EntityNotFoundException e) {
			 return null;
		}
		
		
		
	}


	public void configure() {
		XDBEntity version001 = this.get("XVersion","0.0.1");
		//Si no se configuro antes
		if (version001==null){
			String templateName="XSampleTemplate1";
			Entity template = new Entity("XTemplate",templateName);
			template.setProperty("name","XSampleTemplate1");
			template.setProperty("data" , "Att1;Att2;Att3");
			template.setProperty("attributes" , "att1;att2:att3:att4:att5;att6;att7");
			template.setProperty("properties" , "prop1;prop2");
			template.setProperty("relations" , "parent:XSampleTemplate1");
			
			datastore.put(template);
			String entityMid = "Entity1";
			Entity entity = new Entity("XEntity",templateName + "_" + entityMid);
			entity.setProperty("mid","Entity1");
			entity.setProperty("template",template.getKey());
			entity.setProperty("type",template.getProperty("name"));
			entity.setProperty("data1" , null );
			entity.setProperty("data2" , null );
			entity.setProperty("data3" , null );
			entity.setProperty("active" , true);
			
			datastore.put(entity);
			
			for(int i=1;i<=7;i++){
				Entity attributes = new Entity("XProperty",templateName + "_" +entityMid+"_"+"Att0"+i);
				attributes.setProperty ("templateKey",template.getKey());
				attributes.setProperty ("templateName",template.getProperty("name"));
				attributes.setProperty ("entityKey",entity.getKey());
				attributes.setProperty ("entityMid",template.getProperty("mid"));
				attributes.setProperty ("name",template.getProperty("Att0"+i));
				attributes.setProperty ("value",null);
				datastore.put(attributes);
			}
			
			
			Entity relations = new Entity("XRelation",templateName + "_" +entityMid+"_"+"Att01");
			relations.setProperty ("templateKey",template.getKey());
			relations.setProperty ("templateName",template.getProperty("name"));
			relations.setProperty ("entityKey",entity.getKey());
			relations.setProperty ("entityMid",template.getProperty("mid"));
			relations.setProperty ("name","parent");
			relations.setProperty ("tags",null);
			
			datastore.put(relations);

		}
		
	}


	public void put(String string, String string2) {
		// TODO Auto-generated method stub
		
	}
	 
	 
	
	

}
