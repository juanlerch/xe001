
ui =  { actionMap  -> a1 = widget actionMap.create; a1 } 

update = {prop,value->xe.ui.update(cursor,prop,value)}

widget = { type -> 
           w1 = data type:type
           xuid= xe.xid()
           set xuid:xuid
           xe.ui.onCreate(w1);
           s= load type
           eval s
           w1
        }

/*
mainview = {
          with  globals begin
          	mainview = get "xeMainView"
          	s = load mainview.name
    	  	eval s
          end
          mainview	
} 
 
setmainview = { a-> 
                with  globals begin	
                	set xeMainView:a
                end
                cursor                
              } 
*/