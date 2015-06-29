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

data = {map-> cursor = xe.createDTO(map.type); cursor}

set   = {map-> map.each{
		      key, value -> x = xe.createATT(cursor,key,value);x;
             }
       }

get  = {x -> c= cursor[x]; c.value}       

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