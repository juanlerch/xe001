package com.labs.xe.shared;

import com.google.appengine.api.datastore.Entity;

public class Xonst {
	public static String DB_TEMPLATE = "XTemplate";
	public static String DB_ENTITY =   "XEntity";
	public static String DB_PROPERTY = "XProperty";
	public static String DB_RELATION = "XRelation";
	
	public static final String DB_PROPERTY_ENTITY = "entityKey";
	public static final String DB_PROPERTY_NAME = "name";
	public static final String DB_PROPERTY_VALUE = "value";
	
	public static final String DB_RELATION_NAME =   "parentKey";
	public static final String DB_RELATION_ENTITY = "name";
	public static final String DB_RELATION_CHILD = "childkey";
	
	                                             
	public static final String ServletContext = "servletContext";

	
	public static String XE_xtremEngineServer = "xe";
	//public static String XE_UI = "xe_ui";
	
	public static String SCRIPT_RESULT = "SCRIPT_RESULT";
	public static String SCRIPT_XLINE = "SCRIPT_XCONSOLE";
	public static String SCRIPT_DSL_SESSION = "SCRIPT_DSL_SESSION";
	public static String SCRIPT_SERVER = "SCRIPT_SERVER ";
	
	public static String XE_GLOBALS  =  "XE_GLOBALS";
	public static String XE_GLOBALS_CHANGES  =  "XE_GLOBALS_CHANGES";
	
	public static String SCRIPT_xui = "xui";
	public static String SCRIPT_name = "name";
	public static String SCRIPT_script = "script";
	public static String SCRIPT_result = "result";
	
	//public static String xe_dsl_id = "xe_dsl_id";
	//public static String xe_dsl_name = "xe_dsl_name";
	public static String xe_run_dsl = "xe_run_dsl";

	//public static String groovy ="groovy";
	public static String dsl ="dsl";
	public static String session ="session";

	public static String path_groovy  = "/WEB-INF/groovy/";
	public static String path_groovy_dsl  = "/WEB-INF/groovy/dsl/";
	public static String saved_script_name  = "saved_script_name";
	
	
	public static String xe_cur_script="xe_cur_script";
	
	//public static String utl="utl";
	

	public static String REQUEST_TYPE_RUN_SAVED_SCRIPT = "REQUEST_TYPE_RUN_SAVED_SCRIPT";
	
	public static String INIT_SCRIPT_PATH = "/xe/init.xe";

	public static String xuid="xuid";
	public static String xdbid="xdbid";
	
	
	

	
	
}
