package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class TipeRestController {
	
	@Autowired
	private AnunciosRepositoryJava repositoryJava;
	@Autowired
	private AnunciosRepositorySpring repositorySpring;

	
	
	@RequestMapping("/Buscar")
	public String buscarAnuncioDinamico(@RequestParam String term){
		
		//aqui obtengo todos los titulos que contienen la palabra buscada
		//los guardo en dos ArrayList, uno para cada tabla
		ArrayList<String> arrayJava = repositoryJava.findByTitleArray(term);
		ArrayList<String> arraySpring = repositorySpring.findByTitleArray(term);
		
		
		//Aqui conbino los dos ArrayList en uno solo, se almacenan en arrayJAva 
		for(int i=0; i<arraySpring.size(); i++) {
			arrayJava.add(arraySpring.get(i));
		}
		
		//Creacion del objeto Gson para "Castear" el ArrayList arrayJava en un string con formato Json 
		Gson gson = new Gson();
		String respuesta = gson.toJson(arrayJava);
		
		return respuesta;
	}
	
	
}
