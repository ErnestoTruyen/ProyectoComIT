

$(document).ready( function(){
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
				       
				       				       
				       
				       
});


