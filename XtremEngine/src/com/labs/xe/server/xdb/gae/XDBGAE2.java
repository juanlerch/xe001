package com.labs.xe.server.xdb.gae;

import java.util.ArrayList;
import java.util.List;

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
import com.labs.xe.client.dto.XEDTOFactory;
import com.labs.xe.client.dto.XEIATT;
import com.labs.xe.client.dto.XEIDTO;
import com.labs.xe.server.xdb.XDB;
import com.labs.xe.shared.Xonst;

public class XDBGAE2 implements XDB {
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	XEDTOFactory factory = new XEDTOFactory();
	List<Long> savedKeys  = new ArrayList<Long>();
	private void waitKey(Key k){
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
	
	
	private Key datastorePutAndWait(Entity entity){
		Key k = datastore.put(entity);
		waitKey(k);
		return k;
	}
	
	private Entity findOrCreateEntity(XEIDTO data){
		Entity entity  = null;
		String sxdbid = data.getValueAsString(Xonst.xdbid); //datastorage-id  
		String type = data.getName(); 
		String id   = data.getValueAsString("id");           //user id
		if (id!=null) id=id.toLowerCase();
		
		if (sxdbid != null){ 
			Long xdbid  =  Long.parseLong(sxdbid);
			Key key = KeyFactory.createKey(Xonst.DB_ENTITY, xdbid);
			try {
					entity = datastore.get(key);  //find 
				} catch (EntityNotFoundException e) {
					entity = new Entity(Xonst.DB_ENTITY,xdbid);  	//not found: create with xdbid	
					Key k = datastorePutAndWait(entity);
				}
		} else {
			boolean createNew=false;
			if (id!= null){
				//find by user id
				Query q = new Query(Xonst.DB_ENTITY);
				
			    Filter fid= new FilterPredicate("id",FilterOperator.EQUAL,id);

			    
			    Filter ftype =
			    		  new FilterPredicate("type",
			    		                      FilterOperator.EQUAL,
			    		                      type);
			    Filter filter = CompositeFilterOperator.and(fid,ftype);
			    
			    q.setFilter(filter);
			    
				PreparedQuery pq =  datastore.prepare(q);
				boolean find = false;
				for(Entity e : pq.asIterable()){
					find = true;
					entity = e;
				}
				if (find){
					entity.setProperty("preview", data.getValueAsString("preview"));
					createNew=false;
				}
				else{
					createNew=true;
				}
			}
			else createNew=true;
		if (createNew){	
			entity = new Entity(Xonst.DB_ENTITY); //new 
			entity.setProperty("type",  type );
			entity.setProperty("id",    id );
			entity.setProperty("preview", data.getValueAsString("preview"));
			Key k = datastorePutAndWait(entity);
			data.add(Xonst.xdbid, factory.createAttString(""+ k.getId()));
		}
	} 
		//entity.setProperty(Xonst.xdbid, xdbid);
		return entity;
	}
	
	private Entity findOrCreateProperty(Key entityKey,String name){
		  
		Query q = new Query(Xonst.DB_PROPERTY);
		
	    Filter fname= new FilterPredicate(Xonst.DB_PROPERTY_NAME,FilterOperator.EQUAL,name);

	    
	    Filter fkey =
	    		  new FilterPredicate(Xonst.DB_PROPERTY_ENTITY,
	    		                      FilterOperator.EQUAL,
	    		                      entityKey);
	    Filter filter = CompositeFilterOperator.and(fname,fkey);
	    
	    q.setFilter(filter);
	    
	    
		PreparedQuery pq =  datastore.prepare(q);
		for(Entity e : pq.asIterable()){
			return e;
		}
		
		Entity prop = new Entity(Xonst.DB_PROPERTY);
		prop.setProperty(Xonst.DB_PROPERTY_ENTITY,entityKey);
		prop.setProperty(Xonst.DB_PROPERTY_NAME,name);
		
		return prop;
	
	
}
	
	
	private Entity findOrCreateAtt(Key entityKey,String name,Object value){
		Entity attEntity = findOrCreateProperty(entityKey, name);
		
		if (value instanceof XEIDTO) {
			XEIDTO child = (XEIDTO) value;
			Key childkey  = this.saveAndReturnKey(child);
			attEntity.setProperty(Xonst.DB_PROPERTY_VALUE,childkey);
		}
		else {
			attEntity.setProperty(Xonst.DB_PROPERTY_VALUE,value);
		}
		
		datastorePutAndWait(attEntity);
		return attEntity;
		
	}
	
	private Entity findOrCreateRel(Key entityKey,String name,Key child){
		  
		Query q = new Query(Xonst.DB_RELATION);
		
	    Filter fname= new FilterPredicate(Xonst.DB_RELATION_NAME,FilterOperator.EQUAL,name);

	    
	    Filter fkey =
	    		  new FilterPredicate(Xonst.DB_RELATION_ENTITY,
	    		                      FilterOperator.EQUAL,
	    		                      entityKey);
	    
	    
	    Filter fkey2 =
	    		  new FilterPredicate(Xonst.DB_RELATION_CHILD,
	    		                      FilterOperator.EQUAL,
	    		                      child);

	    
	    Filter filter = CompositeFilterOperator.and(fname,fkey,fkey2);
	    
	    q.setFilter(filter);
	    
	    
		PreparedQuery pq =  datastore.prepare(q);
		for(Entity e : pq.asIterable()){
			return e;
		}
		
		Entity prop = new Entity(Xonst.DB_RELATION);
		prop.setProperty(Xonst.DB_RELATION_ENTITY,entityKey);
		prop.setProperty(Xonst.DB_RELATION_NAME,name);
		prop.setProperty(Xonst.DB_RELATION_CHILD ,child);
		datastorePutAndWait(prop);
		return prop;
		
	}
	
	private Key saveAndReturnKey   (XEIDTO data){
		Entity entity  = findOrCreateEntity(data);
		
		//prevent to make a recursive save
		if (savedKeys.contains(entity.getKey().getId())) 
			return entity.getKey();
		else{
			savedKeys.add(entity.getKey().getId());
		}
		
	   //atributtes;	
		for (String name:data.getEntryKey()){
			Entity prop = findOrCreateAtt(entity.getKey(),name, data.getValueAsObject(name));
		}
		
		
		for (String name:data.getRelEntryKey()){
			List<XEIDTO> dtos = data.getRel(name);
			for (XEIDTO dto:dtos){
				Key childKey = saveAndReturnKey(dto);
				Entity rel = findOrCreateRel(entity.getKey(),name, childKey);
			}
			
		}
		
		if (data.getValueAsString(Xonst.xdbid)==null){
			XEIATT<String> temp = factory.createAttString("" + entity.getKey().getId()); 
			data.add(Xonst.xdbid, temp);
		}
		return entity.getKey();
	}	
		
	
	/*//////////////////////////////////////////*/
	@Override
	public XEIDTO save   (XEIDTO data){
		saveAndReturnKey(data);
		this.savedKeys.clear();
		return data;
	}
	
	@Override
	public XEIDTO delete (XEIDTO data){
		return data;
	}
	
	@Override
	public XEIDTO query  (XEIDTO data){
		return data;
	}
	
}

