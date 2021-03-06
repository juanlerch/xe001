package com.labs.xe.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.labs.xe.client.dto.XEIDTO;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {

	/*String greetServer(String name) throws IllegalArgumentException;
	
	List<String> getTemplates();
	
	XEIDTO query(String type); 
	
	XEIDTO createInstance(String type);
	
	public void save(XEIDTO dto);*/
	
	public XEIDTO request(XEIDTO request);
	
}
