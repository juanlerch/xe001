package com.labs.xe.server;

import com.google.appengine.api.datastore.Entity;
import com.labs.xe.server.xdb.XDB;
import com.labs.xe.server.xdb.XDBEntity;
import com.labs.xe.server.xdb.XDBTemplate;

public class XDataSamplePeople {

	
	public void configure() {
	
		XDBTemplate template = new XDBTemplate("People");
		
		template.setData1("FirstName");
		template.setData2("LastName");
		template.setData3("Email");
		
		template.addAtribute("Street");
		template.addAtribute("Phone");
		
		XDB xdb = new XDB();
		
		xdb.addTemplate(template);
		
		XDBEntity entity = new XDBEntity(template.getName());
		for (int x=0;x < 3;x++ ){
			entity.setTemplate("People");
			entity.setAttribute("FirstName","Juan " + x);
			entity.setAttribute("LastName","Lerch");
			entity.setAttribute("Email","juanlerch@gmail.com");
			entity.setAttribute("Street","Sosa Loyola 1784");
			entity.setAttribute("Phone","15466085");
			xdb.put(entity);
		}
		
	}
	
	
}