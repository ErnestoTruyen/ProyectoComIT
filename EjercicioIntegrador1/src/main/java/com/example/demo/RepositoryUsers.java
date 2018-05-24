package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositoryUsers extends JpaRepository<Usuario, Integer>{
	
	
	@Query(value = "SELECT * FROM Usuario WHERE name=?1 AND password=?2", nativeQuery = true)
	Usuario findByUser(String user,String pass);
	
	
}
