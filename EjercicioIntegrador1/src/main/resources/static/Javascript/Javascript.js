

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
				    	
				       
				       $("#newPost").click(function loadPost(url,funcion){
				    	   var xhttp = new XMLHttpRequest();
				    	   xhttp.onreadystatechange = function(){
				    		   console.log("Aca llego");
				    		   if(this.readyState == 4 && this.status == 200){
				    			   nuevoPost(this);
				    		   }
				    	   }
				    	   
				    	   xhttp.open("GET",url,true);
				    	   xhttp.send();
				    	   
				       });
				       
				       function nuevoPost(xhttp){
				    	   console.log("Aca llego2");
				    	   document.getElementById("newPost").innerHtml = xhttp.responseText;
				       }
				    
				       
				       
				       
				       
});


