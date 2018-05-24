package com.example.demo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnunciosRepositoryJava extends JpaRepository<JavaAnuncio, Integer>{
	
	@Query(value = "SELECT titulo FROM java_anuncio", nativeQuery = true)
	ArrayList<String> findByTitle();
	
	@Query(value = "SELECT * FROM java_anuncio ORDER BY fecha_publicacion DESC limit 3", nativeQuery = true)
	ArrayList<JavaAnuncio> findByLasts();
	
	@Query(value = "SELECT titulo FROM java_anuncio", nativeQuery = true)
	String[] findByTitleArray();
	
	@Query(value = "SELECT titulo FROM java_anuncio WHERE titulo LIKE %?1%", nativeQuery = true)
	ArrayList<String> findByTitleArray(String anuncio);
	
	@Query(value = "SELECT id FROM java_anuncio WHERE titulo=?1", nativeQuery = true)
	String findById(String name);
}
