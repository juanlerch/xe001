
ui =  { action  -> xur = action(); xur } 


update = {obj,prop,value->xe_ui.update(obj,prop,value)}


button = {c=xe_ui.button()
           s= load "Button"
           groovy.evaluate(s)           
           c}

textarea = {c=xe_ui.textArea()
           s= load "TextArea"
           groovy.evaluate(s)
           c}

panel = {  c=xe_ui.panel()
           s= load "Panel"
           groovy.evaluate(s)
           c
        }


window = {
  		   c=xe_ui.dialogBox()
           s= load "DialogBox"
           groovy.evaluate(s)
           c
}        

mainview = {
          xur = get "xeMainView"
          s = load xur.class.simpleName
    	  eval s
          xur
} 

setmainview = { a->
	set "xeMainView", a
} 
