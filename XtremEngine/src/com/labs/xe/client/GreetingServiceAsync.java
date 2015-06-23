package com.labs.xe.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.labs.xe.client.dto.XEIDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	/*void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void getTemplates(AsyncCallback<List<String>> callback);

	void query(String type, AsyncCallback<XEIDTO> callback);

	void save(XEIDTO dto, AsyncCallback<Void> callback);

	void createInstance(String type, AsyncCallback<XEIDTO> callback);

	*/

	void request(XEIDTO request, AsyncCallback<XEIDTO> callback);

	
	
//	void create(XEIDTO className, AsyncCallback<XUIBase> callback);
	
	
	
}
