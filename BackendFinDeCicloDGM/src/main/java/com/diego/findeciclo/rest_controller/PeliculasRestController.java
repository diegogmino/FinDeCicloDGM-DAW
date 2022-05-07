package com.diego.findeciclo.rest_controller;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.diego.findeciclo.model.Pelicula;
import com.diego.findeciclo.service.IPeliculaService;
import com.diego.findeciclo.specification.PeliculaSpecification;

@RestController
@RequestMapping("/peliculas")
public class PeliculasRestController {

	@Autowired
	private IPeliculaService peliculaService;
	
	// Métodos CRUD
	
	@GetMapping("listarTodas")
	public List<Pelicula> listarTodas() {
		return peliculaService.buscarTodas();
	}
	
	@GetMapping("/buscar/{id}")
	public Pelicula listarPorId(@PathVariable int id) {
		return peliculaService.buscarPorId(id);
	}
	
	// Filtro
	@GetMapping("/filtrar")
	public List<Pelicula> filtrar(@RequestParam(required = false) Long codigoBarras, @RequestParam(required = false) String titulo, @RequestParam(required = false) BigDecimal precioMax, @RequestParam(required = false) BigDecimal precioMin, @RequestParam(required = false) String genero) {
		
		Specification<Pelicula> spec = construirSpec(codigoBarras, titulo, genero, precioMax, precioMin);
		
		return peliculaService.filtrar(spec);
		
	}
	
	public List<Pelicula> buscarDestacadas() {
		return null;
	}
	
	// Métodos privados
	private Specification<Pelicula> construirSpec(Long codigoBarras, String titulo, String genero, BigDecimal precioMax, BigDecimal precioMin) {
		
		// Seteamos el objeto spec con la cláusula where a null para que de forma predeterminada haga un findAll normal
        Specification<Pelicula> spec = Specification.where(null);

        if(codigoBarras != null) {
            spec = spec.and(PeliculaSpecification.codigoBarras(codigoBarras));
        }
        
        if(titulo != null) {
            spec = spec.and(PeliculaSpecification.titulo(titulo));
        }

        if(genero != null) {
            spec = spec.and(PeliculaSpecification.genero(genero));
        }

        if(precioMax != null && precioMin != null) {
            spec = spec.and(PeliculaSpecification.precioMinMax(precioMin, precioMax));
        } else if(precioMax != null) {
        	spec = spec.and(PeliculaSpecification.precioMax(precioMax));
        } else if(precioMin != null) {
        	spec = spec.and(PeliculaSpecification.precioMin(precioMin));
        }
        
		return spec;
	}
	
}