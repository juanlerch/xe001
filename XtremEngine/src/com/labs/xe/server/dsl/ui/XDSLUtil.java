package com.labs.xe.server.dsl.ui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

import com.labs.xe.shared.Xonst;


public class XDSLUtil {

	
	ServletContext context;
	
	public XDSLUtil(ServletContext context) {
		this.context=context;
	}
	

	
	public  String loadSavedScript ( String path){
		 
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
