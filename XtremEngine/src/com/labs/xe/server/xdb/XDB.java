package com.labs.xe.server.xdb;

import com.labs.xe.client.dto.XEIDTO;

public interface XDB {

	public XEIDTO save (XEIDTO data);
	
	public XEIDTO delete (XEIDTO data);
	
	public XEIDTO query (XEIDTO data);
	
}

