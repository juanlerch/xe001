
ui =  { action  -> xur = action(); xur } 


update = {prop,value->xe.ui.update(xur,prop,value)}

html =   { c=xe.ui.HTML()
           s= load "HTML"
           xe.evaluate(s)           
           c}

button = {c=xe.ui.button(true)
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
          dto = get "xeMainView"
          xur = instance dto
          s = load xur.class.simpleName
    	  eval s
          xur
} 

setmainview = { a->
	set "xeMainView", a
} 
