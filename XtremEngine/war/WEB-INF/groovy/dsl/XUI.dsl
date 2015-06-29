
ui =  { action  -> cursor = action(); cursor } 

update = {prop,value->xe.ui.update(cursor,prop,value)}

widget = { type -> 
           data type:type
           xuid= xe.xid()
           set xuid:xuid
           xe.ui.update(xuid,type,true);
           s= load type
           xe.evaluate(s)
           cursor
        }

html =   { cursor = widget "HTML";cursor}

button = { cursor = widget "Button";cursor}

textarea = {cursor = widget "TextArea";cursor}

panel = {  cursor = widget "Panel";cursor }


window = {cursor = widget "DialogBox";cursor}        

mainview = {
          go globals
          d = get "xeMainView"
          s = load d.name
    	  eval s
          cursor = d
} 
 
setmainview = { a-> 
                go globals	
                set xeMainView:a
                go back
                cursor                
              } 
