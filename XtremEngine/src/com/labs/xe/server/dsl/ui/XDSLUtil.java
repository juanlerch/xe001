package com.labs.xe.server.dsl.ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

import com.labs.xe.server.XGroovy;
import com.labs.xe.shared.Xonst;


public class XDSLUtil {

	
	ServletContext context;
	
	public XDSLUtil(ServletContext context) {
		this.context=context;
	}
	

	
	public  String loadSavedScript ( String path){
		
		if (XGroovy.cache.containsKey(path)) return (String) XGroovy.cache.get(path);
		 
	    BufferedReader reader;
	    InputStream i =  context.getResourceAsStream(Xonst.path_groovy + path);
		try { 
			reader = new BufferedReader( new InputStreamReader(i));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }
	    
	    reader.close();
	    
	    String s = stringBuilder.toString();
	    XGroovy.cache.put(path, s); //save to cache
	    return s ;
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}

}
	
	
	public  String load (String className){
			if (XGroovy.cache.containsKey("load:" +className)) return (String) XGroovy.cache.get("load:" +className);
			
			String file  = className + ".dsl" ;  
		    BufferedReader reader;
		    InputStream i =  context.getResourceAsStream(Xonst.path_groovy_dsl + className + ".dsl");
			try { 
				reader = new BufferedReader( new InputStreamReader(i));
		    String         line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String         ls = System.getProperty("line.separator");

		    while( ( line = reader.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }
		    
		    reader.close();
		    
		    String s = stringBuilder.toString();
		    //s = s.replace("{xuid}",xuid);
		    XGroovy.cache.put("load:" +className, s); //save to cache
		    return s ;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
