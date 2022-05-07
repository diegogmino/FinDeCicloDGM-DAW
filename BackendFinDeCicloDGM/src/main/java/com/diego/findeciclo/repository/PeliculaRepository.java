package com.diego.findeciclo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.diego.findeciclo.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>, JpaSpecificationExecutor<Pelicula> {
	
	
}
