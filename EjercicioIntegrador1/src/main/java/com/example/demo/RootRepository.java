package com.example.demo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RootRepository extends JpaRepository<Administradores, Integer>{
	
	@Query(value = "SELECT administrador FROM administradores WHERE administrador=?1", nativeQuery = true)
	ArrayList<String> findByAdmin(String user);
	
	@Query(value = "SELECT contrasenia FROM administradores WHERE contrasenia=?1", nativeQuery = true)
	ArrayList<String> findByPass(String pass);
	
	@Query(value = "SELECT hascode FROM administradores WHERE administrador=?1", nativeQuery = true)
	String findByHascode(String admin);
	
}
