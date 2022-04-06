package com.diego.findeciclo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.diego.findeciclo.model.Director;

public interface DirectorRepository extends JpaRepository<Director, Integer>, JpaSpecificationExecutor<Director> {
	
	@Query("SELECT d FROM Director d WHERE d.pais LIKE %?1%")
	public List<Director> filtrarPais(String pais);
	
	@Query("SELECT d FROM Director d WHERE d.nombre LIKE %?1%")
	public List<Director> filtrarNombre(String nombre);
	
	@Query("SELECT d FROM Director d WHERE d.apellido LIKE %?1%")
	public List<Director> filtrarApellido(String apellido);

}
