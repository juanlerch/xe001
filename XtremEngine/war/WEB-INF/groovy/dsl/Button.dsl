
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
    	set  key:pkey , value:myButton 
    	
    go back
} 
