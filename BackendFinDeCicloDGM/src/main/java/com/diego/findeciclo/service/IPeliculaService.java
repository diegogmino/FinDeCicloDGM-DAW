package com.diego.findeciclo.service;

import java.math.BigDecimal;
import java.util.List;

import com.diego.findeciclo.model.Pelicula;

public interface IPeliculaService {

	Pelicula guardarPeli(Pelicula pelicula);
	List<Pelicula> buscarTodas();
	Pelicula buscarPorId(int id);
	Pelicula editarPeli(Pelicula peli);
	void eliminarPeli(int id);
	
	List<Pelicula> filtrarCodigoBarras(int codigo);
	List<Pelicula> filtrarTitulo(String titulo);
	List<Pelicula> filtrarPrecios(BigDecimal precioMin, BigDecimal precioMax);
	List<Pelicula> filtrarDestacada();
	List<Pelicula> filtrarGenero(String genero);
	
}
