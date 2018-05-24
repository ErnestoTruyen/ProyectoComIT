package com.example.demo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JavaAnuncio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String titulo;
	private String textoAnuncio;
	private String codigo;
	private Date fechaPublicacion= new Date();
	
	protected JavaAnuncio() {
		
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTextoAnuncio() {
		return textoAnuncio;
	}

	public void setTextoAnuncio(String textoAnuncio) {
		this.textoAnuncio = textoAnuncio;
	}

	public Date getFechaPublicacion() {
		
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public int getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	
	
	
	
	
}
