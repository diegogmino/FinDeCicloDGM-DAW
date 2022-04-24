package com.diego.findeciclo.controller;

import java.math.BigDecimal;
import java.util.List;

import com.diego.findeciclo.model.Formato;
import com.diego.findeciclo.model.Pelicula;
import com.diego.findeciclo.service.IPeliculaService;
import com.diego.findeciclo.specification.PeliculaSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/admin/peliculas")
public class PeliculasController {
    
    @Autowired
	private IPeliculaService peliculaService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarPeliculas(Model model) {

        List<Pelicula> peliculas = peliculaService.buscarTodas();
        model.addAttribute("peliculas", peliculas);
        return "peliculas/listadoPeliculas";
    
    }

    // Filtro
	@RequestMapping(value = "/filtrar", method = RequestMethod.GET)
	public String filtrar(@RequestParam(required = false) String destacada, @RequestParam(required = false) Formato formato, @RequestParam(required = false) String titulo, @RequestParam(required = false) BigDecimal precioMax, @RequestParam(required = false) BigDecimal precioMin, @RequestParam(required = false) String genero, Model model) {
		

        if(destacada.equals("null")) {
            destacada = null;
        }

		Specification<Pelicula> spec = construirSpec(destacada, formato, titulo, genero, precioMax, precioMin);
        List<Pelicula> peliculas = peliculaService.filtrar(spec);

		model.addAttribute("peliculas", peliculas);

		return "peliculas/listadoPeliculas";
		
	}

    private Specification<Pelicula> construirSpec(String destacada, Formato formato, String titulo, String genero, BigDecimal precioMax, BigDecimal precioMin) {
		
		// Seteamos el objeto spec con la cláusula where a null para que de forma predeterminada haga un findAll normal
        Specification<Pelicula> spec = Specification.where(null);

        if(destacada != null) {
            boolean valor = false;
            if(destacada.equals("Si")) {
                valor = true;
            }
            spec = spec.and(PeliculaSpecification.destacada(valor));
        }

        if(formato != null) {
            spec = spec.and(PeliculaSpecification.formato(formato));
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
