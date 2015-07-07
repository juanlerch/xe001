
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

    with globals begin 
    	pkey = myButton.xuid.value
    	set  key:pkey , value:myButton 
    end
} 
