package com.diego.findeciclo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.diego.findeciclo.model.Pelicula;

public interface IPeliculaService {

	Pelicula guardarPeli(Pelicula pelicula);
	List<Pelicula> buscarTodas();
	List<Pelicula> buscarListaDeseos(List<Integer> lista);
	Pelicula buscarPorId(int id);
	Pelicula editarPeli(Pelicula peli);
	void eliminarPeli(int id);
	
	List<Pelicula> filtrar(Specification<Pelicula> spec);
	Page<Pelicula> filtrarPaginado(Specification<Pelicula> spec, Pageable pageable);
	
}
