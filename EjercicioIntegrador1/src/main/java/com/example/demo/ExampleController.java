package com.example.demo;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ExampleController {
	
	
	@Autowired
	private AnunciosRepositoryJava repositoryJava;
	@Autowired
	private AnunciosRepositorySpring repositorySpring;
	@Autowired
	private RootRepository repositoryRoot;
	@Autowired
	private RepositoryUsers userRepository;
	@Autowired
	private PosteosRepository postRepository;
	@Autowired
	private Environment env;
	
	
	
	@RequestMapping("/")
	public String paginaPrincipal() {
		
		return "PaginaPrincipal";
		
	}
	
	
	//-----------------------------------------------------
	//Metodos principales
	@RequestMapping("/Home")
	public String home(Model modelo,HttpSession sesion) {
		
		//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
		modelo.addAttribute("Header", "HeaderDefault");
		modelo.addAttribute("Navbar", "NavbarDefault");
		modelo.addAttribute("Section", "SectionPaginaPrincipal");
		modelo.addAttribute("Aside", "AsideDefault");
		modelo.addAttribute("Footer", "FooterDefault");
		
		modelo.addAttribute("AnunciosJava", repositoryJava.findByLasts());
		modelo.addAttribute("AnunciosSpring", repositorySpring.findByLasts());
		modelo.addAttribute("pestania", "Página Principal");
		
		//Atributos del modelo para mostrar o no los botones Login/Logout
		
		//Si no esta logueado pone login=true / logout=false
		if(sesion.getAttribute("codigo-autorizacion") == null) {
			modelo.addAttribute("login", true);
			modelo.addAttribute("logout", false);
		//Si esta logueado asigna login=false / logout=true
		}else {
			modelo.addAttribute("login", false);
			modelo.addAttribute("logout", true);
		}
		
		
		return "viewFragment";
	}
	
	@RequestMapping("/ProgramacionJava/{pagina}")
	public String programacionJava(Model modelo,@PathVariable int pagina,HttpSession sesion) {
		
		//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
		modelo.addAttribute("Header", "HeaderDefault");
		modelo.addAttribute("Navbar", "NavbarDefault");
		modelo.addAttribute("Section", "SectionProgramacionJava");
		modelo.addAttribute("Aside", "AsideDefault");
		modelo.addAttribute("Footer", "FooterDefault");
		
		
		//Sector para la paginacion de los anuncios 
		//------------------------------------------------------------------------
		//------------------------------------------------------------------------
		
		//Se le resta 1 a pagina dado que empiezan en cero
		AbstractPageRequest consulta = new QPageRequest((pagina-1),5);
		Page<JavaAnuncio> consultaPageSiguiente=  repositoryJava.findAll(consulta);
		
		
		modelo.addAttribute("Entidad", "java");
		modelo.addAttribute("pestania", "Programación Java");
		modelo.addAttribute("Anuncios", consultaPageSiguiente);
		
			modelo.addAttribute("Pagina", pagina);
			modelo.addAttribute("url", "/ProgramacionJava/");
	
			
			if(pagina==1) {
				modelo.addAttribute("mostrarPaginaBegin", false);
				modelo.addAttribute("mostrarAnterior", false);
				modelo.addAttribute("mostrarPaginaEnd", true);
				modelo.addAttribute("mostrarSiguiente", true);
			}else if(pagina==2) {
				modelo.addAttribute("mostrarAnterior", false);
				modelo.addAttribute("mostrarPaginaBegin", true);
				modelo.addAttribute("mostrarPaginaEnd", true);
				modelo.addAttribute("mostrarSiguiente", true);
			}else if(!consultaPageSiguiente.hasNext()){
				modelo.addAttribute("mostrarAnterior", true);
				modelo.addAttribute("mostrarPaginaBegin", true);
				modelo.addAttribute("mostrarPaginaEnd", false);
				modelo.addAttribute("mostrarSiguiente", false);
			}else {
				modelo.addAttribute("mostrarPaginaBegin", true);
				modelo.addAttribute("mostrarAnterior", true);
				modelo.addAttribute("mostrarPaginaEnd", true);
				modelo.addAttribute("mostrarSiguiente", true);
			}
			
		//------------------------------------------------------------------------
		//------------------------------------------------------------------------
			
			//Atributos del modelo para mostrar o no los botones Login/Logout
			
			//Si no esta logueado pone login=true / logout=false
			if(sesion.getAttribute("codigo-autorizacion") == null) {
				modelo.addAttribute("login", true);
				modelo.addAttribute("logout", false);
			//Si esta logueado asigna login=false / logout=true
			}else {
				modelo.addAttribute("login", false);
				modelo.addAttribute("logout", true);
			}

		
		
		return "viewFragment";
	}
	
	
	
	@RequestMapping("/AplicacionesWeb/{pagina}")
	public String aplicacionesWeb(Model modelo,@PathVariable int pagina, HttpSession sesion) {
		
		//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
		modelo.addAttribute("Header", "HeaderDefault");
		modelo.addAttribute("Navbar", "NavbarDefault");
		modelo.addAttribute("Section", "SectionProgramacionWeb");
		modelo.addAttribute("Aside", "AsideDefault");
		modelo.addAttribute("Footer", "FooterDefault");
		
		
		//Sector para la paginacion de los anuncios 
		//------------------------------------------------------------------------
		//------------------------------------------------------------------------
		
		AbstractPageRequest consulta = new QPageRequest((pagina-1),5);
		Page<SpringAnuncio> consultaPageSiguiente=  repositorySpring.findAll(consulta);
		
		modelo.addAttribute("Entidad", "spring");
		modelo.addAttribute("pestania", "Aplicaciones Web");
		modelo.addAttribute("Anuncios", consultaPageSiguiente);
		
			modelo.addAttribute("Pagina", pagina);
			modelo.addAttribute("url", "/AplicacionesWeb/");
			
			if(pagina==1) {
				modelo.addAttribute("mostrarPaginaBegin", false);
				modelo.addAttribute("mostrarAnterior", false);
				modelo.addAttribute("mostrarPaginaEnd", true);
				modelo.addAttribute("mostrarSiguiente", true);
			}else if(pagina==2) {
				modelo.addAttribute("mostrarAnterior", false);
				modelo.addAttribute("mostrarPaginaBegin", true);
				modelo.addAttribute("mostrarPaginaEnd", true);
				modelo.addAttribute("mostrarSiguiente", true);
			}else if(!consultaPageSiguiente.hasNext()){
				modelo.addAttribute("mostrarAnterior", true);
				modelo.addAttribute("mostrarPaginaBegin", true);
				modelo.addAttribute("mostrarPaginaEnd", false);
				modelo.addAttribute("mostrarSiguiente", false);
			}else {
				modelo.addAttribute("mostrarPaginaBegin", true);
				modelo.addAttribute("mostrarAnterior", true);
				modelo.addAttribute("mostrarPaginaEnd", true);
				modelo.addAttribute("mostrarSiguiente", true);
			}
			
		//------------------------------------------------------------------------
		//------------------------------------------------------------------------
			
			//Atributos del modelo para mostrar o no los botones Login/Logout
			
			//Si no esta logueado pone login=true / logout=false
			if(sesion.getAttribute("codigo-autorizacion") == null) {
				modelo.addAttribute("login", true);
				modelo.addAttribute("logout", false);
			//Si esta logueado asigna login=false / logout=true
			}else {
				modelo.addAttribute("login", false);
				modelo.addAttribute("logout", true);
			}
			
			
		
		return "viewFragment";
	}
	
	@RequestMapping("/Contacto")
	public String contacto(Model modelo, HttpSession sesion) {
		
		//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
		modelo.addAttribute("Header", "HeaderDefault");
		modelo.addAttribute("Navbar", "NavbarDefault");
		modelo.addAttribute("Section", "SectionContacto");
		modelo.addAttribute("Aside", "AsideDefault");
		modelo.addAttribute("Footer", "FooterDefault");
				
		modelo.addAttribute("pestania", "Contacto");
		
		
		//Atributos del modelo para mostrar o no los botones Login/Logout
		
		//Si no esta logueado pone login=true / logout=false
		if(sesion.getAttribute("codigo-autorizacion") == null) {
			modelo.addAttribute("login", true);
			modelo.addAttribute("logout", false);
		//Si esta logueado asigna login=false / logout=true
		}else {
			modelo.addAttribute("login", false);
			modelo.addAttribute("logout", true);
		}
		
		
		return "viewFragment";
	}
	//-----------------------------------------------------
	
	
	//-----------------------------------------------------
	@RequestMapping("/ShowView/{entidad}/{id}")
	public String mostrarAnuncio(Model modelo,@PathVariable String entidad,@PathVariable int id, HttpSession sesion) {
		
		//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
		modelo.addAttribute("Header", "HeaderDefault");
		modelo.addAttribute("Navbar", "NavbarDefault");
		modelo.addAttribute("Section", "SectionAnuncio");
		modelo.addAttribute("Aside", "AsideDefault");
		modelo.addAttribute("Footer", "FooterDefault");
		modelo.addAttribute("Tabla",entidad);

		//Atributos del modelo para mostrar o no los botones Login/Logout
		
		//Si no esta logueado pone login=true / logout=false
		if(sesion.getAttribute("codigo-autorizacion") == null) {
			modelo.addAttribute("login", true);
			modelo.addAttribute("logout", false);
		//Si esta logueado asigna login=false / logout=true
		}else {
			modelo.addAttribute("login", false);
			modelo.addAttribute("logout", true);
		}
		
		//Atributos del modelo para mostrar o no los labels Anterior / Siguiente
		//Por default estan cargados en true los dos
		modelo.addAttribute("mostrarLabelAnterior", true);
		modelo.addAttribute("mostrarLabelSiguiente", true);
		

		//---------------------------------
		//Si pertenece a la tabla java...
		//---------------------------------
		if(entidad.equals("java")) {
			
			//Si es el ultimo anuncio en la tabla...
			if(repositoryJava.findByNextId(id).size() == 0) {
				modelo.addAttribute("mostrarLabelSiguiente", false);
			
			//Si es el primer anuncio en la tabla...	
			}else if(repositoryJava.findByPreviousId(id).size() == 0) {
				modelo.addAttribute("mostrarLabelAnterior", false);
				
			}
			
			String Codigo = repositoryJava.findOne(id).getCodigo();
			String texto = repositoryJava.findOne(id).getTextoAnuncio();
			
			int renglones=0;
			int renglonesTexto = 0;
			
			//Contador de \n del campo codigo
			
			//validacion por si el campo codigo esta cargado como null
			if(Codigo!=null) {
				char[] codigoChanged = Codigo.toCharArray();
				
				//Aqui cargo renglones con la cantidad de enters que tiene el campo Codigo
				for(int i=0; i<codigoChanged.length; i++) {
					if(codigoChanged[i]=='\n') {
						renglones++;
					}
				}
				
				modelo.addAttribute("Renglones", renglones+1);
			}else {
				renglones=3;
				modelo.addAttribute("Renglones", renglones);
			}
			

			//Contador de \n del campo textoAnuncio
			
			//validacion por si el campo textoAnuncio esta cargado como null
			if(texto!=null) {
				char[] codigoChanged = texto.toCharArray();
				
				//Aqui cargo renglones con la cantidad de enters que tiene el campo textoAnuncio
				for(int i=0; i<codigoChanged.length; i++) {
					if(codigoChanged[i]=='\n') {
						renglonesTexto++;
					}
				}
				
				modelo.addAttribute("renglonesTexto", renglonesTexto+3);
			}else {
				renglonesTexto=3;
				modelo.addAttribute("renglonesTexto", renglonesTexto);
			}
			//Atributo con el objeto AnuncioJava cargado
			modelo.addAttribute("anuncio", repositoryJava.findOne(id));
			
			
			
		//---------------------------------
		//Si pertenece a la tabla Spring...
		//---------------------------------			
			
		}else if(entidad.equals("spring")) {
			
			//Si es el ultimo anuncio en la tabla...
			if(repositorySpring.findByNextId(id).size() == 0) {
				modelo.addAttribute("mostrarLabelSiguiente", false);
			
			//Si es el primer anuncio en la tabla...	
			}else if(repositorySpring.findByPreviousId(id).size() == 0) {
				modelo.addAttribute("mostrarLabelAnterior", false);
				
			}
			
			
			String Codigo = repositorySpring.findOne(id).getCodigo();
			
			String texto = repositorySpring.findOne(id).getTextoAnuncio();
			
			int renglones=0;
			int renglonesTexto = 0;
			
			//Contador de \n del campo codigo
			
			//validacion por si el campo codigo esta cargado como null
			if(Codigo!=null) {
				
				char[] codigoChanged = Codigo.toCharArray();
				
				//Aqui cargo renglones con la cantidad de enters que tiene el campo Codigo
				for(int i=0; i<codigoChanged.length; i++) {
					if(codigoChanged[i]=='\n') {
						renglones++;
					}
				}
			
				modelo.addAttribute("Renglones", renglones+1);
			}else {
				renglones=3;
				modelo.addAttribute("Renglones", renglones);
			}
			
			//Contador de \n del campo textoAnuncio
			
			//validacion por si el campo textoAnuncio esta cargado como null
			if(texto!=null) {
				char[] codigoChanged = texto.toCharArray();
				
				//Aqui cargo renglones con la cantidad de enters que tiene el campo textoAnuncio
				for(int i=0; i<codigoChanged.length; i++) {
					if(codigoChanged[i]=='\n') {
						renglonesTexto++;
					}
				}
				
				modelo.addAttribute("renglonesTexto", renglonesTexto+3);
			}else {
				renglonesTexto=3;
				modelo.addAttribute("renglonesTexto", renglonesTexto);
			}

			
			
			//Atributo con el objeto AnuncioSpring cargado
			modelo.addAttribute("anuncio", repositorySpring.findOne(id));
			
		}else {
			return "redirect:/Home";
		}
		
		
		
		return "viewFragment";
		
	}
	//-----------------------------------------------------
	
	//-----------------------------------------------------
	//-----------------------------------------------------
	@ResponseBody
	@RequestMapping("/MostrarAnuncio-ajax/{entidad}/{id}/{anuncio}")
	public String anuncioNext(@PathVariable String entidad, @PathVariable int id, @PathVariable String anuncio,Model modelo,HttpSession sesion) {
		
		//Atributos del modelo para mostrar o no los botones Login/Logout
		
		//Si no esta logueado pone login=true / logout=false
		if(sesion.getAttribute("codigo-autorizacion") == null) {
			modelo.addAttribute("login", true);
			modelo.addAttribute("logout", false);
		//Si esta logueado asigna login=false / logout=true
		}else {
			modelo.addAttribute("login", false);
			modelo.addAttribute("logout", true);
		}
		
		
		//VARIABLES Y OBJETOS
		int renglones=1;
		int renglonesTexto=1;
		/*Estas variables son para contar cuantos enters tiene los campo codigo/textoAnuncio de un anuncio
		 * y de esa forma asignar ese valor al alto del textarea que lo va a mostrar*/
		
		int ID;
		/*Esta variable es para almacenar el campo id del anuncio que se quiere mostrar
		 * id minuscula es el id anterior al que se quiere mostrar (ID)
		 * ID mayuscula es el id que se quiere mostrar
		 * id minuscula < ID mayuscula  siempre-----*/
		String labelAnterior="";
		String labelSiguiente="";
		/*Estas son para almacenar tags label y concatenarlos en el html que retorna el metodo
		 * Si se los carga entonces los "botones" Anterior / Siguiente aparecen para el usuario*/
		JavaAnuncio anuncioJava;
		SpringAnuncio anuncioSpring;
		
		
		if(entidad.equals("java")) {
			
			
			
			
			if(anuncio.equals("siguiente")) {
				
				//Aqui consigo el id del siguiente anuncio en la tabla
				ID = repositoryJava.findByNextId(id).get(0);
				//Si NO es el ultimo anuncio de la tabla...
				if(repositoryJava.findByNextId(ID).size() != 0) {
					
					labelSiguiente = "<label class=\"anuncioGeneric btnSiguiente\"\r\n" + 
							"	                 data-id=\""+ID+"\"  data-entidad=\""+entidad+"\" data-anuncio='siguiente'\r\n" + 
							"			         >Siguiente</label>\r\n";
				}
				
				labelAnterior= "<label class=\"anuncioGeneric btnSiguiente\"\r\n" + 
						"				   data-id=\""+ID+"\"  data-entidad=\""+entidad+"\" data-anuncio='anterior'\r\n" +
						"                   >Anterior</label>\r\n";
				
				
				
			}else if (anuncio.equals("anterior")) {
				
				//Aqui consigo el id del anuncio anterior en la tabla
				ID = repositoryJava.findByPreviousId(id).get(0);
				//Si NO es el primer anuncio de la tabla..
				if(repositoryJava.findByPreviousId(ID).size() != 0) {
					
					labelAnterior= "<label class=\"anuncioGeneric btnSiguiente\"\r\n" + 
							"				   data-id=\""+ID+"\"  data-entidad=\""+entidad+"\" data-anuncio='anterior'\r\n" +
							"                   >Anterior</label>\r\n";
				}
				
				labelSiguiente = "<label class=\"anuncioGeneric btnSiguiente\"\r\n" + 
						"	                 data-id=\""+ID+"\"  data-entidad=\""+entidad+"\" data-anuncio='siguiente'\r\n" + 
						"			         >Siguiente</label>\r\n";
				
				
				
			} else {
				ID = id;
				
			}
			
			
			String Codigo = repositoryJava.findOne(ID).getCodigo();
			
			//validacion por si el campo codigo esta cargado como null
			if(Codigo!=null) {
				char[] codigoChanged = Codigo.toCharArray();
				
				//Aqui cargo renglones con la cantidad de enters que tiene el campo Codigo
				for(int i=0; i<codigoChanged.length; i++) {
					if(codigoChanged[i]=='\n') {
						renglones++;
					}
				}
				
				
			}else {
				renglones=3;
				
			}
			
			
			String texto = repositoryJava.findOne(ID).getTextoAnuncio();
			
			//validacion por si el campo textoAnuncio esta cargado como null
			if(texto!=null) {
				char[] codigoChanged = texto.toCharArray();
				
				//Aqui cargo renglones con la cantidad de enters que tiene el campo textoAnuncio
				for(int i=0; i<codigoChanged.length; i++) {
					if(codigoChanged[i]=='\n') {
						renglonesTexto++;
					}
				}
				renglonesTexto = renglonesTexto + 2;
				
				
			}else {
				renglonesTexto=3;
				
			}
			
			//Obtengo el anuncio de la tabla Java con el ID mayuscula
			anuncioJava = repositoryJava.findOne(ID);
			
			String newHtml = " <script th:inline=\"javascript\" src=\"/Javascript/Javascript.js\"></script>" + 
					"			<div id=\"remplazar\">" +
			        "           <div class=\"row anuncioGeneric\">\r\n" + 
					"	        "+labelAnterior + 
					"			<h3 class=\"autoMargin\">"+anuncioJava.getTitulo()+"</h3>\r\n" + 
					"	        "+labelSiguiente + 
					"		  </div>\r\n" + 
					"		  \r\n" + 
					"		  <div class=\"row anuncioGeneric\">\r\n" + 
					"		  \r\n" + 
					"		  	<textarea rows=\""+renglones+"\" cols=\"140\" class=\"autoMargin textarea\" disabled=\"disabled\">"	+
										  anuncioJava.getCodigo()+"</textarea><br></br>\r\n" + 
					"			\r\n" + 
					"			<textarea class=\"autoMargin textareaTexto\" rows=\""+renglonesTexto+"\" cols=\"140\" disabled=\"disabled\">"+anuncioJava.getTextoAnuncio()+"</textarea>\r\n" + 
					"			\r\n" + 
					"		  </div>" +
					"         </div>";
			
			return newHtml;
			
			
			
		}else if(entidad.equals("spring")) {
			

			if(anuncio.equals("siguiente")) {
				
				//Aqui consigo el id del siguiente anuncio en la tabla
				ID = repositorySpring.findByNextId(id).get(0);
				//Si NO es el ultimo anuncio de la tabla...
				if(repositorySpring.findByNextId(ID).size() != 0) {
					
					labelSiguiente = "<label class=\"anuncioGeneric btnSiguiente\"\r\n" + 
							"	                 data-id=\""+ID+"\"  data-entidad=\""+entidad+"\" data-anuncio='siguiente'\r\n" + 
							"			         >Siguiente</label>\r\n";
				}
				
				labelAnterior= "<label class=\"anuncioGeneric btnSiguiente\"\r\n" + 
						"				   data-id=\""+ID+"\"  data-entidad=\""+entidad+"\" data-anuncio='anterior'\r\n" +
						"                   >Anterior</label>\r\n";
				
			}else if (anuncio.equals("anterior")) {
				
				//Aqui consigo el id del anuncio anterior en la tabla
				ID = repositorySpring.findByPreviousId(id).get(0);
				//Si NO es el primer anuncio de la tabla...
				if(repositorySpring.findByPreviousId(ID).size() != 0) {
					
					labelAnterior= "<label class=\"anuncioGeneric btnSiguiente\"\r\n" + 
							"				   data-id=\""+ID+"\"  data-entidad=\""+entidad+"\" data-anuncio='anterior'\r\n" +
							"                   >Anterior</label>\r\n";
				}
				
				labelSiguiente = "<label class=\"anuncioGeneric btnSiguiente\"\r\n" + 
						"	                 data-id=\""+ID+"\"  data-entidad=\""+entidad+"\" data-anuncio='siguiente'\r\n" + 
						"			         >Siguiente</label>\r\n";
				

				
			} else {
				ID = id;
				
			}

			
			
			String Codigo = repositorySpring.findOne(ID).getCodigo();
			
			//validacion por si el campo codigo esta cargado como null
			if(Codigo!=null) {
				
				char[] codigoChanged = Codigo.toCharArray();
				
				//Aqui cargo renglones con la cantidad de enters que tiene el campo Codigo
				for(int i=0; i<codigoChanged.length; i++) {
					if(codigoChanged[i]=='\n') {
						renglones++;
					}
				}
			
				
			}else {
				renglones=3;
				
			}
			
			
			String texto = repositorySpring.findOne(ID).getTextoAnuncio();
			
			//validacion por si el campo textoAnuncio esta cargado como null
			if(texto!=null) {
				char[] codigoChanged = texto.toCharArray();
				
				//Aqui cargo renglones con la cantidad de enters que tiene el campo textoAnuncio
				for(int i=0; i<codigoChanged.length; i++) {
					if(codigoChanged[i]=='\n') {
						renglonesTexto++;
					}
				}
				renglonesTexto = renglonesTexto + 2;
				
				
			}else {
				renglonesTexto=3;
				
			}			
			
			//Obtengo el anuncio de la tabla Spring con el ID mayuscula
			anuncioSpring = repositorySpring.findOne(ID);
			
			String newHtml = " <script th:inline=\"javascript\" src=\"/Javascript/Javascript.js\"></script>" + 
					"          <div id=\"remplazar\">" +
			        "           <div class=\"row anuncioGeneric\">\r\n" + 
					"		   "+labelAnterior + 
					"			<h3 class=\"autoMargin\">"+anuncioSpring.getTitulo()+"</h3>\r\n" + 
					"		   "+labelSiguiente + 
					"		  </div>\r\n" + 
					"		  \r\n" + 
					"		  <div class=\"row anuncioGeneric\">\r\n" + 
					"		  \r\n" + 
					"		  	<textarea rows=\""+renglones+"\" cols=\"140\" class=\"autoMargin textarea\" disabled=\"disabled\">"	+
										  anuncioSpring.getCodigo() +"</textarea><br></br>\r\n" + 
					"			\r\n" + 
					"			<textarea class=\"autoMargin textareaTexto\" rows=\""+renglonesTexto+"\" cols=\"140\" disabled=\"disabled\">"+anuncioSpring.getTextoAnuncio()+"</textarea>\r\n" + 
					"			\r\n" + 
					"		  </div>" +
					"         </div>";
			
			
			return newHtml;
			
		}else {
			return "<p>Algo salio mal</p>";
		}
	
	}
	//-----------------------------------------------------
	//-----------------------------------------------------

	
	
	//hacer la validacion de nombres de anuncios en las dos tablas 
	@RequestMapping("/ListarAnuncios")
	public String listaAnuncios(@RequestParam String nombreAnuncio, Model modelo) {
		
		//variables que guardan el id de tipo entero
		int idNumberJava,idNumberSpring;
		
		//Objetos que guardan el id de tipo String
		String idSpring = repositorySpring.findById(nombreAnuncio);
		String idJava = repositoryJava.findById(nombreAnuncio);
		
		//Si el anuncio pertenece a la tabla anunciosSpring...
		if(idSpring != null) {
			
			idNumberSpring = Integer.parseInt(idSpring);
			return "redirect:/ShowView/spring/" + idNumberSpring;
		
		//Si el anuncio pertenece a la tabla anunciosJava...
		}else if(idJava != null) {
	
			idNumberJava = Integer.parseInt(idJava);
			return "redirect:/ShowView/java/" + idNumberJava;
		}else {		
			
			//acá se deberia redirigir a un section que diga   "lo sentimos, no se ha encontrado el anuncio"
			return "redirect:/Home";
		}
	}
	
	
	//-----------------------------------------------------------------------
	//-----------------------------------------------------------------------
	
	//SECCION PARA SESIONES DE USUARIOS
	
	
	//-----------------------------------------------------------------------
	//-----------------------------------------------------------------------
	@RequestMapping("/FormularioUsuario")
	public String formularioUsuario(Model modelo) {
		
		//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
		modelo.addAttribute("Header", "HeaderDefault");
		modelo.addAttribute("Navbar", "NavbarDefault");
		modelo.addAttribute("Section", "SectionFormularioUsuario");
		modelo.addAttribute("Aside", "AsideDefault");
		modelo.addAttribute("Footer", "FooterDefault");
		
		
		return "viewFragment";
	}	
	
	
	@RequestMapping("/CrearUsuario")
	public String crearUsuario(Model modelo,
							   HttpSession sesion,
							   @RequestParam String nombre,
							   @RequestParam String contra) {
		
		
		
		//si el usuario no esta registrado en la BBDD...
		if(userRepository.findByNombre(nombre) == null) {
			//creo un hascode para esta sesion y creo el usuario en la BBDD con sus datos
			String codigo = UUID.randomUUID().toString();
			sesion.setAttribute("codigo-autorizacion", codigo);
			sesion.setAttribute("user", nombre);
			sesion.setAttribute("password", contra);
			Usuario user = new Usuario();
			user.setActivo(true);
			user.setHascode(codigo);
			user.setName(nombre);
			user.setPassword(contra);
	
			userRepository.save(user);
			
			return "redirect:/Home";
			
		}else {
			
			//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
			modelo.addAttribute("Header", "HeaderDefault");
			modelo.addAttribute("Navbar", "NavbarDefault");
			modelo.addAttribute("Section", "SectionNoEncontrado");
			modelo.addAttribute("Aside", "AsideDefault");
			modelo.addAttribute("Footer", "FooterDefault");
			
			return "viewFragment";
		}
	}
	
	@RequestMapping("/LoguearUsuario")
	public String loguerUsuario(HttpSession sesion,@RequestParam String user,@RequestParam String pass,@RequestParam String tema, Model modelo) {
		
				
		
		Usuario usuario = userRepository.findByUser(user, pass); 
		if(usuario!= null) {
			
			
			sesion.setAttribute("codigo-autorizacion", usuario.getHascode());
			sesion.setAttribute("user", user);
			sesion.setAttribute("password", pass);
			if(tema.equals("")) {
				return "redirect:/Home";
			}else {
				return "redirect:/Foro/" + tema;
			}
		}else {
			
			//Atributos del modelo para mostrar o no los botones Login/Logout
			
			//Si no esta logueado pone login=true / logout=false
			if(sesion.getAttribute("codigo-autorizacion") == null) {
				modelo.addAttribute("login", true);
				modelo.addAttribute("logout", false);
			//Si esta logueado asigna login=false / logout=true
			}else {
				modelo.addAttribute("login", false);
				modelo.addAttribute("logout", true);
			}			
			
			
			//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
			modelo.addAttribute("Header", "HeaderDefault");
			modelo.addAttribute("Navbar", "NavbarDefault");
			modelo.addAttribute("Section", "SectionError");
			modelo.addAttribute("Aside", "AsideDefault");
			modelo.addAttribute("Footer", "FooterDefault");
			
			return "viewFragment";
		}
	}
	
	@RequestMapping("/Cerrarsesion")
	public String cerrarSesion(HttpSession sesion) {
		
		if(sesion.getAttribute("codigo-autorizacion") != null) {
			sesion.setAttribute("codigo-autorizacion", null);
			sesion.setAttribute("user", null);
			sesion.setAttribute("password", null);
		}
		
		return "redirect:/Home";
	}
	
	
	@RequestMapping("/Foro/{tema}")
	public String homeForo(Model modelo,HttpSession sesion,@PathVariable String tema) {
		
		//Atributos del modelo para mostrar o no los botones Login/Logout
		
		//Si no esta logueado pone login=true / logout=false
		if(sesion.getAttribute("codigo-autorizacion") == null) {
			modelo.addAttribute("login", true);
			modelo.addAttribute("logout", false);
		//Si esta logueado asigna login=false / logout=true
		}else {
			modelo.addAttribute("login", false);
			modelo.addAttribute("logout", true);
		}

		
		Usuario usuarioActual=null;;
		
		if(sesion.getAttribute("user") != null && sesion.getAttribute("password") != null) {
		
			usuarioActual = userRepository.findByUser(
													(String)sesion.getAttribute("user"), 
													(String)sesion.getAttribute("password")
													 );
		}
		
		if(usuarioActual != null && 
				sesion.getAttribute("codigo-autorizacion").equals(usuarioActual.getHascode())) {
			
			//Atributos para renderizar dinamicamente el foro
			
			modelo.addAttribute("tema", tema);
			
			
			//Cargo los atribitos del modelo con los Names de los html que quiero que se carguen en el layout
			modelo.addAttribute("Header", "HeaderDefault");
			modelo.addAttribute("Navbar", "NavbarDefault");
			modelo.addAttribute("Section", "SectionForo");
			modelo.addAttribute("Aside", "AsideDefault");
			modelo.addAttribute("Footer", "FooterDefault");
			
			return "viewFragment";
			
		}
	
		return "redirect:/Home";
	}
	
	
	//-----------------------------------------------------------------------ç
	
	@RequestMapping("/Chats")
	public String homeChats(Model modelo,HttpSession sesion) {
		
		//Atributos del modelo para mostrar o no los botones Login/Logout
		
		//Si no esta logueado pone login=true / logout=false
		if(sesion.getAttribute("codigo-autorizacion") == null) {
			modelo.addAttribute("login", true);
			modelo.addAttribute("logout", false);
		//Si esta logueado asigna login=false / logout=true
		}else {
			modelo.addAttribute("login", false);
			modelo.addAttribute("logout", true);
		}

		
		Usuario usuarioActual=null;;
		
		if(sesion.getAttribute("user") != null && sesion.getAttribute("password") != null) {
		
			usuarioActual = userRepository.findByUser(
													(String)sesion.getAttribute("user"), 
													(String)sesion.getAttribute("password")
													 );
		}
		
		if(usuarioActual != null && 
				sesion.getAttribute("codigo-autorizacion").equals(usuarioActual.getHascode())) {
			
			//Cargo los atribitos del modelo con los Names de los html que quiero que se carguen en el layout
			modelo.addAttribute("Header", "HeaderDefault");
			modelo.addAttribute("Navbar", "NavbarDefault");
			modelo.addAttribute("Section", "SectionChats");
			modelo.addAttribute("Aside", "AsideDefault");
			modelo.addAttribute("Footer", "FooterDefault");
			
			return "viewFragment";
			
		}
	
		return "redirect:/Home";
	}
	
	
	//-----------------------------------------------------------------------
	
	//-----------------------------------------------------------------------
	
	//Url para enviar un Email
	@RequestMapping("/ApiGmail")
	public String contacto(@RequestParam String asunto, @RequestParam String mensaje){
		
		
		Email email = EmailBuilder.startingBlank()
			    .from("Ernesto Truyen", "ernesto1995truyen@gmail.com")
			    .to("ernesto Truyen", "ernesto1995truyen@gmail.com")
			    .withSubject(asunto)
			    .withPlainText(mensaje)
			    .buildEmail();

			MailerBuilder
			  .withSMTPServer(env.getProperty("SMTP_URL"), 587, "apikey", env.getProperty("SMTP_PASSWORD"))
			  .buildMailer()
			  .sendMail(email);
		
		
		return "redirect:/Contacto";
	}
	
	
	//CRUD DE LA  BBDD
	
	//-----------------------------------------------------------------------
	
	//                    ACCESO ADMINISTRADOR
	//-----------------------------------------------------------------------
	@RequestMapping("/AccesoRoot")
	public String accesoRoot(){
		return "acceso";
	}
	//-----------------------------------------------------------------------
	//-----------------------------------------------------------------------
		@RequestMapping("/Validar")
		public String validar(String Usuario, String Password, String enviado,HttpSession sesion){
			//SI LLEGA ACA DESDE EL FORMULARIO
			if(enviado != null){
				//QUERY CON EL PARAMETRO USUARIO
				//ME TRAE UN STRING
				String admin = repositoryRoot.findByAdmin(Usuario);
				System.out.println(admin);
				String pass = repositoryRoot.findByPass(Password);
				System.out.println(pass);
				if( !admin.equals(null) && !pass.equals(null)){
					System.out.println("paso el null");	
					//Consigo de la BBDD el hascode para esta sesion
					String codigo = repositoryRoot.findByHascode(Usuario);
					sesion.setAttribute("codigo-autorizacion", codigo);
					sesion.setAttribute("Admin", admin);

					
					return "redirect:/BBDD/MostrarJava/"+ admin;
				}
			}
			return "redirect:/AccesoRoot";
		}
		//-----------------------------------------------------------------------
	
	//------------------ENTIDAD ANUNCIO_JAVA---------------------------------
	//-----------------------------------------------------------------------
	//-----------------------------------------------------------------------
	
	//                      MOSTRAR ANUNCIOS
	//---------------------------------------------------------------
	@RequestMapping("/BBDD/MostrarJava/{admin}")
	public String mostrarBBDDJava(Model modelo,HttpSession sesion,@PathVariable String admin) {

		if(!sesion.getAttribute("codigo-autorizacion").equals(repositoryRoot.findByHascode(admin)) ) {
	
			return "redirect:/Home";
		}
		
		
		modelo.addAttribute("JavaAnuncios", repositoryJava.findAll());
		return "BBDDJava";
	}
	//---------------------------------------------------------------
	
	//                     INSERTAR ANUNCIO
	//---------------------------------------------------------------
	@RequestMapping("/BBDD/nuevoAnuncioJava")
	public String formularioAnuncioJava() {
		return "nuevoAnuncioJava";
	}
	
	@RequestMapping("/BBDD/InsertarJava")
	public String insertarJava(Model modelo, JavaAnuncio anuncio,HttpSession sesion) {
		
		repositoryJava.save(anuncio);
		
		return "redirect:/BBDD/MostrarJava/" + sesion.getAttribute("Admin");
	}
	//---------------------------------------------------------------
	
	//                      EDITAR ANUNCIO
	//---------------------------------------------------------------
	@RequestMapping("/BBDD/EditarJava/{id}")
	public String editarJava(@PathVariable int id, Model modelo) {
		modelo.addAttribute("AnuncioUpdate", repositoryJava.getOne(id));
		modelo.addAttribute("Seccion", "Java");
		return "mostrarAnuncio";
	}
	
	@RequestMapping("/BBDD/UpdateAnuncio/Java/{id}")
	public String updateAnuncio(@PathVariable int id, String Titulo, String Texto,HttpSession sesion) {
		JavaAnuncio anuncio = repositoryJava.findOne(id);
		anuncio.setTitulo(Titulo);
		anuncio.setTextoAnuncio(Texto);
		
		repositoryJava.save(anuncio);
		return "redirect:/BBDD/MostrarJava/" + sesion.getAttribute("Admin");
	}
	//---------------------------------------------------------------
	
	//                       BORRAR ANUNCIO
	//---------------------------------------------------------------
	@RequestMapping("/BBDD/BorrarJava/{id}")
	public String deleteAnuncioJava(@PathVariable int id,HttpSession sesion) {
		repositoryJava.delete(id);
		return "redirect:/BBDD/MostrarJava/" + sesion.getAttribute("Admin");
	}
	//---------------------------------------------------------------
	//-----------------------------------------------------------------------
	//-----------------------------------------------------------------------
	
	//-----------------ENTIDAD ANUNCIO_SPRING--------------------------------
	
	
	
	//                    MOSTRAR ANUNCIOS
	//---------------------------------------------------------------
	@RequestMapping("/BBDD/MostrarSpring")
	public String mostrarBBDDSpring(Model modelo) {
		modelo.addAttribute("SpringAnuncios", repositorySpring.findAll());
		return "BBDDSpring";
	}
	//---------------------------------------------------------------
	
	//                    INSERTAR ANUNCIO
	//---------------------------------------------------------------
	@RequestMapping("/BBDD/nuevoAnuncioSpring")
	public String formularioAnuncioSpring() {
		return "nuevoAnuncioSpring";
	}
	
	@RequestMapping("/BBDD/InsertarSpring")
	public String insertarSpring(Model modelo, SpringAnuncio anuncio) {
		
		repositorySpring.save(anuncio);
		
		return "redirect:/BBDD/MostrarSpring";
	}
	//----------------------------------------------------------------	
	
	//                     EDITAR ANUNCIO
	//---------------------------------------------------------------
	@RequestMapping("/BBDD/EditarSpring/{id}")
	public String editarSpring(@PathVariable int id,Model modelo) {
		modelo.addAttribute("AnuncioUpdate", repositorySpring.getOne(id));
		modelo.addAttribute("Seccion", "Spring");
		return "mostrarAnuncio";
	}

	@RequestMapping("/BBDD/UpdateAnuncio/Spring/{id}")
	public String updateAnuncioSpring(@PathVariable int id, String Titulo, String Texto) {
		SpringAnuncio anuncio=repositorySpring.findOne(id);
		anuncio.setTitulo(Titulo);
		anuncio.setTextoAnuncio(Texto);
		
		repositorySpring.save(anuncio);
		return "redirect:/BBDD/MostrarSpring";
	}
	//---------------------------------------------------------------
	
	//                      BORRAR ANUNCIO
	//---------------------------------------------------------------
	@RequestMapping("/BBDD/BorrarSpring/{id}")
	public String deleteAnuncioSpring(@PathVariable int id) {
		repositorySpring.delete(id);
		return "redirect:/BBDD/MostrarSpring";
	}
	//---------------------------------------------------------------
	
	
	//---------------------------------------------------------------
	@ResponseBody
	@RequestMapping("/Posteos/nuevo-hilo")
	public String nuevoposteo(@RequestParam String titulo, 
							  @RequestParam String texto,
							  @RequestParam String tema) {
		
		Posteos nuevoPost = new Posteos();
		nuevoPost.setTema(tema);
		nuevoPost.setTitulo(titulo);
		nuevoPost.setTexto(texto);
		
		postRepository.save(nuevoPost);
		
		return "";
	}
	//---------------------------------------------------------------
	
	//---------------------------------------------------------------
		@ResponseBody
		@RequestMapping("/Posteos/LastFive")
		public ArrayList<String> ultimos5(@RequestParam String tema) {
			
			ArrayList<String> lista = postRepository.buscarPorUltimos5(tema);
			System.out.println(lista);
			
			return lista;
		}
		//---------------------------------------------------------------
		
	
}













