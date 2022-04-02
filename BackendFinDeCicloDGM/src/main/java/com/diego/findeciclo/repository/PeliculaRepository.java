package com.diego.findeciclo.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.diego.findeciclo.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

	@Query("SELECT p FROM Pelicula p WHERE p.codigoBarras = ?1")
	public List<Pelicula> filtrarCodigoBarras(int codigoBarras);
	
	@Query("SELECT p FROM Pelicula p WHERE p.titulo LIKE %?1%")
	public List<Pelicula> filtrarTitulo(String titulo);
	
	@Query("SELECT p FROM Pelicula p WHERE p.precio BETWEEN ?1 AND ?2")
	public List<Pelicula> filtrarPrecio(BigDecimal precioMin, BigDecimal precioMax);
	
	@Query("SELECT p FROM Pelicula p WHERE p.destacada = TRUE")
	public List<Pelicula> filtrarDestacada();
	
	@Query("SELECT p FROM Pelicula p WHERE p.genero LIKE %?1%")
	public List<Pelicula> filtrarGenero(String genero);
	
}
