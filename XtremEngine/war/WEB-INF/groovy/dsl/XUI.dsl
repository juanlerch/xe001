
ui =  { action  -> xur = action(); xur } 


update = {obj,prop,value->xe.ui.update(obj,prop,value)}


button = {c=xe.ui.button()
           s= load "Button"
           xe.evaluate(s)           
           c}

textarea = {c=xe.ui.textArea()
           s= load "TextArea"
           xe.evaluate(s)
           c}

panel = {  c=xe.ui.panel()
           s= load "Panel"
           xe.evaluate(s)
           c
        }


window = {
  		   c=xe.ui.dialogBox()
           s= load "DialogBox"
           xe.evaluate(s)
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
