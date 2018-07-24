

$(document).ready( function(){
	
						//Variables globales,para diversos usos
						var Tema= "";
	
	
	                    //----------------------------------------------
						
						
						//Helpers
						function setAttributeHidden(){
							
					    	   $(document).ready( function(){
					    		   
					    		   document.getElementById("hidden-variable").setAttribute("value",Tema);
					    		   
					    	   });
					    }
					       
					    function opcionForo(id){
					    	
					    	   if(id == "list-Java-list1"){
					    		   Tema = "Java";
					    		   console.log(Tema);
					    		   setAttributeHidden();
					    	   }else if(id == "list-Spring-list1"){
					    		   Tema = "Spring";
					    		   setAttributeHidden();
					    	   }else if(id == "list-Html-list1"){
					    		   Tema = "HTML";
					    		   setAttributeHidden();
					    	   }else if(id == "list-Css-list1"){
					    		   Tema = "Css";
					    		   setAttributeHidden();
					    	   }else if(id == "list-Javascript-list1"){
					    		   Tema = "Javascript";
					    		   setAttributeHidden();
					    	   }else{
					    		   console.log("Nuevo Tema.");
					    	   }
					    }
						
						//-----------------------------------------------
						
				       $("#textarea1").keyup( function(){
			        						      var urlIncomplete = "/Buscar"
				    						      $("#textarea1").autocomplete({
				    						    	  source: urlIncomplete
				    						      });
				    						  });
				       
				       $('.btnSiguiente').click( function(){
				    	   debugger;
				    	   var id = $(this).data("id");
				    	   debugger;
				    	   var entidad = $(this).data("entidad");
				    	   debugger;
				    	   var anuncio = $(this).data("anuncio");
				    	   debugger;
				    	   console.log(id);
				    	   console.log(entidad);
				    	   debugger;
				    	   $.ajax({
				    		   url: "/MostrarAnuncio-ajax/" + entidad + "/" + id + "/" + anuncio
				    	   }).done(function(respuesta){
				    		   console.log("OK");
				    		   $( respuesta ).replaceAll( "#remplazar" );
				    		   
				    	   });
				       });
				       
				       
				       
				       
				          
				       $('body').on('click', '#list-tab1 a', function(){
				    		 console.log($(this).attr('id'));
				    	     opcionForo($(this).attr('id'));
				       });
				    	
				       $("#crearHilo").click(function(){
				    	   var datos = document.getElementsByClassName("formulario");
				    	   var xhttp = new XMLHttpRequest();
				    	   
				    	   var titulo = datos[0].value;
				    	   var texto = datos[1].value;
				    	   var tema = datos[2].value;
				    	   console.log(tema);
				    	   
				    	   xhttp.onreadystatechange = function(){
				    		   console.log("entro al javascript");
				    		   
				    		   if(this.readyState == 4 && this.status == 200){
				    			   console.log("Carga satisfactoria.");
				    			   
				    		   }
				    	   }
				    	   xhttp.open("GET","/Posteos/nuevo-hilo?titulo="+titulo+"&texto="+texto+"&tema="+tema,true);
				    	   xhttp.send();
				       });
				       
				       $("#lastFive").ready(function(){
				    	   
				    	   var tema = document.getElementById("temaLastFive").value;
				    	   
				    	   var xhttp = new XMLHttpRequest();
				    	   xhttp.onreadystatechange = function(){
				    		   
				    		   if(this.readyState == 4 && this.status == 200){
				    			   var arr = this.responseText.split('"');
				    			   console.log(arr);
				    			   
				    			   var longitud = arr.length;
				    			   console.log(longitud);
				    			   for(i = 0; i<longitud; i++){
				    				   if(arr[i] != "[" && arr[i] != "]" && arr[i] != "," && longitud != 1){
				    					   $( ".posteo" ).append( "<a href='#'><div class='row borde-redondeado enmarcado-interno background-post panel-body'>"+arr[i]+"</div></a>" );
				    				   }
				    			   }
				    		   }
				    	   }
				    	   xhttp.open("GET","/Posteos/LastFive?tema="+tema,true);
				    	   xhttp.send();
				       });
				       /*$(".newPost").click(function loadPost(){
				    	   
				    	   var xhttp = new XMLHttpRequest();
				    	   var TemaForo = document.getElementsByClassName("newPost");
				    	   var partDinamic = TemaForo[0].getAttribute("id");
				    	   console.log(partDinamic);
				    	   
				    	   xhttp.open("GET","/Posteos/nuevo-hilo/" + partDinamic,true);
				    	   xhttp.send();
				    	   
				    	   xhttp.onreadystatechange = function(){
				    		   console.log("entro al javascript");
				    		   if(this.readyState == 4 && this.status == 200){
				    			   nuevoPost(this);
				    		   }
				    	   }
				    	   
				    	   
				       });
				       
				       function nuevoPost(xhttp){
				    	   console.log(xhttp.responseText);
				    	   document.getElementById("section-foro1").innerHTML = xhttp.responseText;
				       }
				       */
				       
				       
				       
				       
});


