package com.example.demo;

import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
		
		
		return "viewFragment";
	}
	
	@RequestMapping("/ProgramacionJava/{pagina}")
	public String programacionJava(Model modelo,@PathVariable int pagina) {
		
		//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
		modelo.addAttribute("Header", "HeaderDefault");
		modelo.addAttribute("Navbar", "NavbarDefault");
		modelo.addAttribute("Section", "SectionProgramacionJava");
		modelo.addAttribute("Aside", "AsideDefault");
		modelo.addAttribute("Footer", "FooterDefault");
		
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
			
		
		return "viewFragment";
	}
	
	
	
	@RequestMapping("/AplicacionesWeb/{pagina}")
	public String aplicacionesWeb(Model modelo,@PathVariable int pagina) {
		
		//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
		modelo.addAttribute("Header", "HeaderDefault");
		modelo.addAttribute("Navbar", "NavbarDefault");
		modelo.addAttribute("Section", "SectionProgramacionWeb");
		modelo.addAttribute("Aside", "AsideDefault");
		modelo.addAttribute("Footer", "FooterDefault");
		
		
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
			
			//modelo.addAttribute("Anuncios", "Ha cargado exitosamente");
		
		return "viewFragment";
	}
	
	@RequestMapping("/Contacto")
	public String contacto(Model modelo) {
		
		//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
		modelo.addAttribute("Header", "HeaderDefault");
		modelo.addAttribute("Navbar", "NavbarDefault");
		modelo.addAttribute("Section", "SectionContacto");
		modelo.addAttribute("Aside", "AsideDefault");
		modelo.addAttribute("Footer", "FooterDefault");
				
		modelo.addAttribute("pestania", "Contacto");
		return "viewFragment";
	}
	//-----------------------------------------------------
	
	
	//-----------------------------------------------------
	@RequestMapping("/ShowView/{entidad}/{id}")
	public String mostrarAnuncio(Model modelo,@PathVariable String entidad,@PathVariable int id) {
		
		//Cargo los atribitos del layout con los Names de los html que quiero que se carguen
		modelo.addAttribute("Header", "HeaderDefault");
		modelo.addAttribute("Navbar", "NavbarDefault");
		modelo.addAttribute("Section", "SectionAnuncio");
		modelo.addAttribute("Aside", "AsideDefault");
		modelo.addAttribute("Footer", "FooterDefault");


		
		if(entidad.equals("java")) {
			
			String Codigo = repositoryJava.findOne(id).getCodigo();
			int renglones=0;
			//validacion por si el campo codigo esta cargado como null
			if(Codigo!=null) {
				char[] codigoChanged = Codigo.toCharArray();
				
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
			//Atributo con el objeto AnuncioJava cargado
			modelo.addAttribute("anuncio", repositoryJava.findOne(id));
			
		}else if(entidad.equals("spring")) {
			
			String Codigo = repositorySpring.findOne(id).getCodigo();
			int renglones=0;
			//validacion por si el campo codigo esta cargado como null
			if(Codigo!=null) {
				
				char[] codigoChanged = Codigo.toCharArray();
				
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
			//Atributo con el objeto AnuncioSpring cargado
			modelo.addAttribute("anuncio", repositorySpring.findOne(id));
			
		}else {
			return "redirect:/Home";
		}
		
		//modelo.addAttribute("Anuncios", "Ha cargado exitosamente");
		
		return "viewFragment";
		
	}
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
	public String loguerUsuario(HttpSession sesion,@RequestParam String user,@RequestParam String pass, Model modelo) {
		
				
		
		Usuario usuario = userRepository.findByUser(user, pass); 
		if(usuario!= null) {
			sesion.setAttribute("codigo-autorizacion", usuario.getHascode());
			return "redirect:/Home";
		}else {
			
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
		}
		
		return "redirect:/Home";
	}
	
	
	//-----------------------------------------------------------------------
	//-----------------------------------------------------------------------
	
	
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
				ArrayList<String> admin = repositoryRoot.findByAdmin(Usuario);
				ArrayList<String> pass = repositoryRoot.findByPass(Password);
				if( !admin.isEmpty() && !pass.isEmpty()){
					
					//Consigo de la BBDD el hascode para esta sesion
					String codigo = repositoryRoot.findByHascode(Usuario);
					sesion.setAttribute("codigo-autorizacion", codigo);

					
					return "redirect:/BBDD/MostrarJava/"+ admin.get(0);
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

		if(sesion.getAttribute("codigo-autorizacion").equals(repositoryRoot.findByHascode(admin)) ) {
	
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
	public String insertarJava(Model modelo, JavaAnuncio anuncio) {
		
		repositoryJava.save(anuncio);
		
		return "redirect:/BBDD/MostrarJava";
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
	public String updateAnuncio(@PathVariable int id, String Titulo, String Texto) {
		JavaAnuncio anuncio = repositoryJava.findOne(id);
		anuncio.setTitulo(Titulo);
		anuncio.setTextoAnuncio(Texto);
		
		repositoryJava.save(anuncio);
		return "redirect:/BBDD/MostrarJava";
	}
	//---------------------------------------------------------------
	
	//                       BORRAR ANUNCIO
	//---------------------------------------------------------------
	@RequestMapping("/BBDD/BorrarJava/{id}")
	public String deleteAnuncioJava(@PathVariable int id) {
		repositoryJava.delete(id);
		return "redirect:/BBDD/MostrarJava";
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
	
	
	
	
}














