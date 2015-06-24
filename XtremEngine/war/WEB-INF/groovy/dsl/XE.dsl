eval = {a->
        a = xe.preProcessScript(a)
        r = xe.evaluate(a) 
        r}               

load = { p -> s = xe.load(p)
              s }

run =  {a -> s = xe.util.loadSavedScript(a);
                  eval s 
       } 

go  =  {x -> xur = x
             s = load xur.name
             if (s!=null) eval s
             xur
             }
             
get  = {x -> c= xe.get(x);
        c
 }

set  = {x,y -> xe.set(x,y);}


data = { name-> xur = xe.createDTO(name); xur}

att  = {map-> map.each{
		      key, value -> x = xe.createATT(xur,key,value);x;
             }
       }

rel  = {map-> map.each{
		      key, value -> x = xe.createREL(xur,key,value);x;
             }
       }


demo = {run "/demo/demo.xe"}

//------------------------------------------------------------//



s = load "XUI"
eval s

s = load "XDB"
eval s

xresponse = eval xe_cur_script

//session.setAttribute("xtremEngineServer",xtremEngineServer)
xresponse