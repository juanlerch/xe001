package com.labs.xe.server.xdb.gae;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Text;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;
import com.labs.xe.client.dto.XEDTO;
import com.labs.xe.client.dto.XEDTOConstants;
import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.client.dto.XEIDTO;


public class XDBGae implements Serializable {
	public static String CONFIG =   "XConfig";
	public static String TEMPLATE = "XTemplate";
	public static String ENTITY =   "XEntity";
	public static String PROPERTY = "XProperty";
	public static String RELATION = "XRelation";
	public static String LOG = "XLog";
	
	
	private String asString(Entity e,String propName){
		Object o= e.getProperty(propName);
		if (o!=null){
			return o.toString();
		}
		return null;
	}
	
	
	public XEIDTO createDTO(String name){
		
		
		XEDTOFactory factory = new XEDTOFactory();;	 
	    	
		
		XEIDTO dto = factory.create(name);
		
		return dto;
		
		/*if (factory ==null) factory = AutoBeanFactorySource.create(XEDTOFactory.class);
		AutoBean<XEIDTO> idto = factory.xeidto();
		XEIDTO dto = idto.as();
		return dto;*/ 
		
	}
	
	public void save (List<XEIDTO> dto){
		for(XEIDTO d:dto) save(d);
	}
	
	public void save (XEIDTO dto){
		
		XDBTemplate template = this.loadTemplate(dto.getName()); 
		
		if (template!=null) //is an Entity.
		{	XDBEntity entity = new XDBEntity(template.getName());
			String id = null;
			
			for(String key:dto.getEntryKey()){
				
				if(key.equalsIgnoreCase("id")){
					id = dto.get(key).getValue().toString();
				}
				else{
					entity.setAttribute(key, dto.get(key).getValue().toString());
				}
			}
			entity.setId(id);
			this.put(entity);
			
		}
		
		if(dto.getRelEntryKey()!=null){
			for(String key:dto.getRelEntryKey()){
				List<XEIDTO> dtoset = dto.getRel(key);
				save(dtoset);
			}
		}
		
		
		
	}
	
	
	 public XEIDTO query (XDBQuery query){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			 
		XDBTemplate template = this.loadTemplate(query.getTemplate()); 
		 
		XEIDTO dto = this.createDTO(XEDTOConstants.QUERY_RESULT + template.getName());
		
		Query q = new Query(ENTITY);
		Filter filter = new FilterPredicate("template",FilterOperator.EQUAL, query.getTemplate());
		q.setFilter(filter);
		 
		PreparedQuery pq = datastore.prepare(q);
		List<String> l = new ArrayList<String>();
		
		String[] data = new String[5];
		data[0] =  "id";
		data[1] =  template.getData()[0];
		data[2] =  template.getData()[1];
		data[3] =  template.getData()[2];
		data[4] =  "active";
		
		XEDTOFactory factory = new XEDTOFactory();
		
		dto.add(XEDTOConstants.HEADER,factory.createAttListOfString(data)) ;
		
		
		for (Entity result : pq.asIterable()) {
			XEIDTO child= this.createDTO(template.getName());
			String id = "" + result.getKey().getId();
			  child.add("id",    factory.createAttString(id));
			  child.add(data[1], factory.createAttString(asString(result,"data1")));
			  child.add(data[2], factory.createAttString(asString(result,"data2")));
			  child.add(data[3], factory.createAttString(asString(result,"data3")));
			  child.add("active", factory.createAttString(asString(result,"active")));
			  dto.addRel(XEDTOConstants.QUERY_DATA,child);
			}
		 
		//todo agregar las pr
		return dto;
		 
	 }
	
	 public XDBEntity get(String template,String id){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			
		Key k = KeyFactory.createKey(template, template+"-"+id);
		
		try {
			
			Entity e = datastore.get(k);

			XDBEntity xe = new XDBEntity(template);
			xe.setEntity(e);
			
			
			return xe;
		} catch (EntityNotFoundException e) {
			 return null;
		}
		
	}
	 


	public List<String> getTemplates(String filter){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		List <String> result = new ArrayList<String>();
		Query q = new Query(TEMPLATE);
		
		if (filter != null && filter.length()>0)
		{
			  Filter f= new FilterPredicate("name",FilterOperator.EQUAL,filter);
			  q.setFilter(f);

		}
		PreparedQuery pq =  datastore.prepare(q);
		for(Entity e : pq.asIterable()){
			Object o = e.getKey().getName();
			if (o!=null){
				result.add(o.toString());
			}
		}
		
		return result;
		
	}  
	 
	
	
	public Object getConfigParamValue(String name){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity config;
		Key key = KeyFactory.createKey(XDBGae.CONFIG, name);
		try {
			config = datastore.get(key);
		} catch (EntityNotFoundException e) {
			return null;
		}

		Object o = config.getProperty("value");
		return o;
	}

	
	public void putConfigParam(String name,Object value){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity version;
		Key key = KeyFactory.createKey(XDBGae.CONFIG, name);
		try {
			version = datastore.get(key);
		} catch (EntityNotFoundException e) {
			version = new Entity(XDBGae.CONFIG,"version");
		}

		version.setProperty("value", value);
		datastore.put(version);
	}
	
	public void configure() {
		
		if (getConfigParamValue("version")==null) 
		{
			putConfigParam("version", "v0001");
		}
		
	}


	public void put(String string, String string2) {
		// TODO Auto-generated method stub
		
	}

	public static XDBGae getInstance() {
		//todo: ver si puedo hacer singleton, o debo crear siempre uno nuevo
		return new XDBGae();
	}
	
	
	
	private String joinString(List<String> l){
		String r="" ;
		int count = 1;
		if(l.size()>0){
			for(String s:l){
				if (count<l.size())
					r+= s + ";";
				else
					r +=s; 
			}
			return r;
		};
		return null;	
	}
	
	public void addTemplate(XDBTemplate template) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity e = new Entity(TEMPLATE,template.getName());
		String data = template.getData()[0] + ";" + template.getData()[1] + ";" + template.getData()[2];  
		
				
		e.setProperty("data" , data);
		
		e.setProperty("attributes" , joinString(template.getAttributeName()));
		
		datastore.put(e);
	}
	
	public XDBTemplate loadTemplate(String name) {
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		
			List <String> result = new ArrayList<String>();
			Key key = KeyFactory.createKey(TEMPLATE, name);
			Entity e;
			try {
				e = datastore.get(key);
				
				XDBTemplate t = new XDBTemplate(name);
				String[] s = ((String) e.getProperty("data")).split(";");
				t.setData1(s[0]);
				t.setData2(s[1]);
				t.setData3(s[2]);
				
				for(String s1:((String) e.getProperty("attributes")).split(";")){
					t.addAtribute(s1);
				}
				
				
				return t;	
			} catch (EntityNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return null;
			
			}
	
	private int in(String s1 ,String[] s2){
		int i=0;
		for(String t:s2){
			if (s1.equalsIgnoreCase(t)) return i;
			
			i++;
		}
		return -1;
	}
	
	
	void waitKey(Key k){
		int t = 0;
		while (!k.isComplete() && t<100) {
			try {
				wait(10);
				t++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	public void put(XDBEntity entity) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		boolean insert=false;
		XDBTemplate template  = loadTemplate(entity.getTemplate());
		Entity e = null;
		if (entity.getId()!=null && entity.getId().length()>0){
				Long lid = Long.parseLong(entity.id);
				Key k =  KeyFactory.createKey(ENTITY,lid);
				try {
					e = datastore.get(k);
					insert=false;
				} catch (EntityNotFoundException e1) {
					insert=true;
				}
			}
		else insert=true;	
			
		if (insert){
			e = new Entity(ENTITY);
			e.setProperty ("template",entity.getTemplate());		
			e.setProperty ("active",true);
			datastore.put(e);
		}

	
		for (String name:entity.getValues().keySet()){
			String value= entity.getValues().get(name);
			int i = in(name,template.getData());
			
			if(i==-1) {
				Entity prop = null;
				waitKey(e.getKey());
				prop = getProperty(e.getKey(), name);

				prop.setProperty ("value",value);
				
					
				datastore.put(prop);
				
			}
			else if (i==0){
				e.setProperty("data1", value);
			}
			else if (i==1){
				e.setProperty("data2", value);
			}
			else if (i==2){
				e.setProperty("data3", value);
			}
			
			datastore.put(e);
		}
		
		
		
	}
	
	public Entity getProperty(Key entityKey,String name){
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		  
			Query q = new Query(PROPERTY);
			
		    Filter fname= new FilterPredicate("name",FilterOperator.EQUAL,name);

		    
		    Filter fkey =
		    		  new FilterPredicate("entityKey",
		    		                      FilterOperator.EQUAL,
		    		                      entityKey);
		    Filter filter = CompositeFilterOperator.and(fname,fkey);
		    
		    q.setFilter(filter);
		    
		    
			PreparedQuery pq =  datastore.prepare(q);
			for(Entity e : pq.asIterable()){
				return e;
			}
			
			Entity prop = new Entity(XDBGae.PROPERTY);
			prop.setProperty ("entityKey",entityKey);
			prop.setProperty ("name",name);
			return prop;
		
		
	}
	




	public static void log(String log,String message){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		XDBGae xdb = new XDBGae();
		Entity e = new Entity(LOG);
		e.setProperty ("log",log);		
		e.setProperty ("message",message);
		e.setProperty ("time", Calendar.getInstance().getTime());

		String s = "LOG:" + log + " | " + message;
		System.out.println(s);

		datastore.put(e);
	}

	
	public static void log(String log,Throwable t){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		String s = "LOG:" + log + " | " + t.getMessage() + " | " +  sw.toString();
		System.out.println(s);
		XDBGae xdb = new XDBGae();
		
		Entity e = new Entity(LOG);
		e.setProperty ("log",log);		
		e.setProperty ("message",t.getMessage());
		Text text = new Text(sw.toString());
		e.setProperty ("stack",text);
		e.setProperty ("time", Calendar.getInstance().getTime());
		datastore.put(e);
	}


	public XEIDTO createInstance(String type) {
		
		XDBTemplate template = this.loadTemplate(type); 
				 
		XEIDTO dto = this.createDTO(template.getName());
				

		String[] data = new String[5];
		data[0] =  "id";
		data[1] =  template.getData()[0];
		data[2] =  template.getData()[1];
		data[3] =  template.getData()[2];
		data[4] =  "active";
		
		XEDTOFactory factory=new XEDTOFactory();
		dto.add(data[0], factory.createAttString(null));
		dto.add(data[1], factory.createAttString(null));
		dto.add(data[2], factory.createAttString(null));
		dto.add(data[3], factory.createAttString(null));
		dto.add(data[4], factory.createAttString(null));

		return dto;		
	}
	
	

}
