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
             eval s
             xur
             }
             
get  = {x -> c= xe.get(x);
        c
 }

set  = {x,y -> xe.set(x,y);}

data = { name-> xur = xe.createDTO(name); xur}

att  = {name,value-> x = xe.createATT(xur,name,value);x}

rel = {name,value-> x = xe.createREL(xur,name,value);x}


demo = {run "/demo/demo.xe"}

//------------------------------------------------------------//



s = load "XUI"

eval s

xresponse = eval xe_cur_script

//session.setAttribute("xtremEngineServer",xtremEngineServer)
xresponse