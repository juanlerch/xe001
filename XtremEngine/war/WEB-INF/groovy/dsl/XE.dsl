//-------------------------------------------------------------------------------/
eval = {a->
        a = xe.preProcessScript(a)
        r = xe.evaluate(a) 
        r}               

load = { p -> s = xe.load(p)
              s }

run =  {a -> s = xe.util.loadSavedScript(a);
                  eval s 
       } 

go  =  {param -> if (param==back){
                    if (back.empty) cursor = null;
                    else cursor =  back.pop()
                 } 
                 else {       
                       back.push(cursor)
                       cursor = param
                       s = load cursor.name
                       if (s!=null) eval s
             }
             cursor
       }
             
//-------------------------------------------------------------------------------/

data = {map-> cursor = xe.createDTO(map.type); cursor
        map.remove("type")
		map.each{
		     key, value -> x = xe.createATT(cursor,key,value);x;
	    }
	    cursor
       }

set   = {map->
            if (map.size() != 2) { 
                map.each{
			      key, value -> x = xe.createATT(cursor,key,value);x;
	             }
            }
			else 
				if (map.size()==2 && map["key"] != null && map["value"] != null )
				{
				     x = xe.createATT(cursor,map["key"],map["value"]);x;
				}
			else 
	            map.each{
			      key, value -> x = xe.createATT(cursor,key,value);x;
	             }
       }

get  = {x -> c= cursor[x]
             if (c!=null) c.value
             else null}       

rel  = {map-> map.each{
		      key, value -> x = xe.createREL(cursor,key,value);x;
             }
       }


//------------------------------------------------------------//


demo = {run "/demo/demo.xe"}

s = load "XUI"
eval s

s = load "XDB"
eval s

xresponse = eval xe_cur_script

xresponse