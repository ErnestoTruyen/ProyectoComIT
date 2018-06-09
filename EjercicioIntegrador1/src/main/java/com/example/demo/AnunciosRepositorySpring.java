package com.example.demo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnunciosRepositorySpring extends JpaRepository<SpringAnuncio, Integer>{

	@Query(value = "SELECT titulo FROM spring_anuncio", nativeQuery = true)
	ArrayList<String> findByTitle();
	
	@Query(value = "SELECT * FROM spring_anuncio ORDER BY fecha_publicacion DESC limit 3", nativeQuery = true)
	ArrayList<SpringAnuncio> findByLasts();
	
	@Query(value = "SELECT titulo FROM spring_anuncio", nativeQuery = true)
	String[] findByTitleArray();
	
	@Query(value = "SELECT titulo FROM spring_anuncio WHERE titulo LIKE %?1%", nativeQuery = true)
	ArrayList<String> findByTitleArray(String anuncio);
	
	@Query(value = "SELECT id FROM spring_anuncio WHERE titulo=?1", nativeQuery = true)
	String findById(String name);
	
	@Query(value = "SELECT id FROM spring_anuncio WHERE id>:ID ORDER BY id ASC LIMIT 1", nativeQuery = true)
	ArrayList<Integer> findByNextId(@Param("ID") int ID);
	
	@Query(value = "SELECT id FROM spring_anuncio WHERE id<:ID ORDER BY id DESC LIMIT 1", nativeQuery = true)
	ArrayList<Integer> findByPreviousId(@Param("ID") int ID);
}





