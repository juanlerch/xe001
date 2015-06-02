
ui =  { action  -> xur = action(); xur } 


update = {obj,prop,value->xe_ui.update(obj,prop,value)}


button = {c=xe_ui.button()
           s=utl.load("Button",c.xuid)
           groovy.evaluate(s)
           c}

textarea = {c=xe_ui.textArea()
           s=utl.load("TextArea",c.xuid)
           groovy.evaluate(s)
           c}

panel = {  c=xe_ui.panel()
           s=utl.load("Panel",c.xuid)
           groovy.evaluate(s)
           c
        }


window = {
  		   c=xe_ui.dialogBox()
           s=utl.load("DialogBox",c.xuid)
           groovy.evaluate(s)
           c
}        
