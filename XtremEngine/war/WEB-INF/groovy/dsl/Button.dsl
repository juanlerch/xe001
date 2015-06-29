
caption = {pcaption->
           set    caption:pcaption
		   update "caption",pcaption
           cursor
          }

onClick = {
    s->
    set name:"onClick"     
    set onClick:s
    myButton = cursor

    go globals
    	pkey = myButton.xuid.value
    	set pkey:myButton //save de boton as global
    
    go back
} 
