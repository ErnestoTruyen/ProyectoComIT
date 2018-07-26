package com.example.demo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PosteosRepository extends JpaRepository<Posteos, Integer>{

	
	@Query(value = "SELECT * FROM posteos WHERE tema=?1 ORDER BY id DESC limit 5", nativeQuery = true)
	ArrayList<Posteos> buscarPorUltimos5(String tema);
	
	
}







