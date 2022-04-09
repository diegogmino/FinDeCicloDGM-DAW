package com.diego.findeciclo.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.diego.findeciclo.model.Pelicula;

public interface IPeliculaService {

	Pelicula guardarPeli(Pelicula pelicula);
	List<Pelicula> buscarTodas();
	Pelicula buscarPorId(int id);
	Pelicula editarPeli(Pelicula peli);
	void eliminarPeli(int id);
	
	List<Pelicula> filtrarDestacada();
	List<Pelicula> filtrar(Specification<Pelicula> spec);
	
}
