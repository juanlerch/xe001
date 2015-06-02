
//-------------------------------------------------//
xe_run_dsl = {id,name-> 
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
        if (c==null) c=c=dsl.get(x);
        c
 }

set  = {x,y -> if(x instanceof String ) { session.setAttribute(x,y);}
               else {dsl.put(x,y)} 
              }



demo = {run "/demo/demo.xe"}

//------------------------------------------------------------//
s = load "XUI"

eval s

eval xe_cur_script
