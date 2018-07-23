package com.example.demo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PosteosRepository extends JpaRepository<Posteos, Integer>{

	
	@Query(value = "SELECT titulo FROM posteos WHERE tema=?1", nativeQuery = true)
	ArrayList<String> buscarPorUltimos5(String tema);
	
}







