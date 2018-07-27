

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
				    	   console.log(tema);
				    	   
				    	   var xhttp = new XMLHttpRequest();
				    	   xhttp.onreadystatechange = function(){
				    		   
				    		   if(this.readyState == 4 && this.status == 200){
				    			   //------------------------------------------------------
				    			   var arreglo = JSON.parse(this.responseText);
				    			   
				    			   //------------------------------------------------------
				    			   
				    			   var longitud2 = arreglo.length;
				    			  
				    			   for(i = 0; i<longitud2; i++){
				    				   
				    				   $( ".posteo" ).append( "<a href='#'><div id='"+arreglo[i].id+"' class='posteoAjax1 row borde-redondeado enmarcado-interno background-post panel-body'>"+arreglo[i].titulo+"</div></a>" );
				    				   
				    			   }
				    		   }
				    	   }
				    	   xhttp.open("GET","/Posteos/LastFive?tema="+tema,true);
				    	   xhttp.send();
				       });
				    
				       // .mostrarPosteo-->clase del contenedor padre
				       // .on--> Metodo de JQuery para escuchar eventos de elementos que se cargan dinamicamente
				       // click--> evento a escuchar por la funcion
				       // .posteoAjax1--> clase de los elementos hijos que escuchan el evento click
				       $('.mostrarPosteo').on('click','.posteoAjax1', function(){
				    	    
				    	   var id = $(this).attr("id");
				    	   console.log(id);
				    	   $.ajax({
				    		   url: "/BuscarPosteoPorId/" + id
				    	   }).done(function(respuesta){
				    		   console.log("OK");
				    		  
				    		   $(respuesta).replaceAll( "#section-foro1" );
				    		   
				    	   });
				       });
				       
				       
				       
});


