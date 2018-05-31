package com.example.demo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administradores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String administrador;
	private String contrasenia;
	private String hascode;
	private Date fechaPublicacion= new Date();
	
	protected Administradores() {
		
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

	

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String cantrasenia) {
		this.contrasenia = cantrasenia;
	}

	public String getHascode() {
		return hascode;
	}

	public void setHascode(String hascode) {
		this.hascode = hascode;
	}

	
		
	
	
	
}
