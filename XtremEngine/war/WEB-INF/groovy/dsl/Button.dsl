
caption = {caption->
           att    caption:caption
		   update "caption",caption
           xur
          }

onClick = {
    s->
    set xur.xuid.value,xur //save de boton as global
    att name:"onClick"     
    att onClick:s
} 
