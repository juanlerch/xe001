
//-------------------------------------------------//
xe_run_dsl_old = {id,name-> 
              o = sdl.get(id)
              s = o.get(name)  
              groovy.evaluate(s)
              }


                                                                                             
eval = {a->
        a = xgroovy.preProcessScript(a)
        r = groovy.evaluate(a) 
        r}               

load = { p -> s = utl.load(p,"") }

run =  {a -> s = utl.loadSavedScript(a);
                  eval s 
       } 



go  =  {x -> xur = x}
get  = {x -> c= session.getAttribute(x);
        c
 }

set  = {x,y -> session.setAttribute(x,y);}



demo = {run "/demo/demo.xe"}

//------------------------------------------------------------//

s = load "XUI"

eval s

xresponse = eval xe_cur_script

//session.setAttribute("xtremEngineServer",xtremEngineServer)
xresponse