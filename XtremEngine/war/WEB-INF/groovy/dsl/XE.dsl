eval = {a->
        a = xe.preProcessScript(a)
        r = xe.evaluate(a) 
        r}               

load = { p -> s = xe.load(p) }

run =  {a -> s = xe.util.loadSavedScript(a);
                  eval s 
       } 

go  =  {x -> xur = x}
get  = {x -> c= xe.get(x);
        c
 }

set  = {x,y -> xe.set(x,y);}



demo = {run "/demo/demo.xe"}

//------------------------------------------------------------//

s = load "XUI"

eval s

xresponse = eval xe_cur_script

//session.setAttribute("xtremEngineServer",xtremEngineServer)
xresponse