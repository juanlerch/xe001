
ui =  { action  -> xur = action(); xur } 

update = {prop,value->xe.ui.update(xur,prop,value)}

widget = { type -> 
           data type
           xuid= xe.xid()
           att "xuid",xuid
           xe.ui.update(xuid,type,true);
           s= load type
           xe.evaluate(s)
           xur
        }

html =   { xur = widget "HTML";xur}

button = { xur = widget "Button";xur}

textarea = {xur = widget "TextArea";xur}

panel = {  xur = widget "Panel";xur }


window = {xur = widget "DialogBox";xur}        

mainview = {
          d = get "xeMainView"
          s = load d.name
    	  eval s
          xur = d
} 

setmainview = { a->	set "xeMainView", a} 
